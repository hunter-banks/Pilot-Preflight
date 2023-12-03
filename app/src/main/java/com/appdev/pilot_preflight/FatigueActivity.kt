package com.appdev.pilot_preflight

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox

class FatigueActivity : AppCompatActivity() {
    private lateinit var continueEmotion: Button
    private lateinit var symptomCheckbox: CheckBox
    private lateinit var dutyLimitsCheckbox: CheckBox
    private lateinit var mentalCheckbox: CheckBox
    private lateinit var workHoursCheckbox: CheckBox
    private lateinit var managementCheckbox: CheckBox

    private val prefsFileName = "MyPrefs" // Custom name for shared preferences file

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fatigue)

        continueEmotion = findViewById(R.id.imsafe_continue_emotion_button)
        symptomCheckbox = findViewById(R.id.fatigue_symptom_checkbox)
        dutyLimitsCheckbox = findViewById(R.id.fatigue_duty_limits)
        mentalCheckbox = findViewById(R.id.fatigue_mental)
        workHoursCheckbox = findViewById(R.id.fatigue_work_hours)
        managementCheckbox = findViewById(R.id.fatigue_management)

        val prefs = getSharedPreferences(prefsFileName, Context.MODE_PRIVATE)

        symptomCheckbox.isChecked = prefs.getBoolean("fatigue_symptom_checkbox", false)
        dutyLimitsCheckbox.isChecked = prefs.getBoolean("fatigue_duty_limits", false)
        mentalCheckbox.isChecked = prefs.getBoolean("fatigue_mental", false)
        workHoursCheckbox.isChecked = prefs.getBoolean("fatigue_work_hours", false)
        managementCheckbox.isChecked = prefs.getBoolean("fatigue_management", false)

        continueEmotion.setOnClickListener(this::continueEmotion)
    }

    private fun continueEmotion(view: View) {
        val prefs = getSharedPreferences(prefsFileName, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("fatigue_symptom_checkbox", symptomCheckbox.isChecked)
        editor.putBoolean("fatigue_duty_limits", dutyLimitsCheckbox.isChecked)
        editor.putBoolean("fatigue_mental", mentalCheckbox.isChecked)
        editor.putBoolean("fatigue_work_hours", workHoursCheckbox.isChecked)
        editor.putBoolean("fatigue_management", managementCheckbox.isChecked)

        val allCheckboxesSelected =
            symptomCheckbox.isChecked && dutyLimitsCheckbox.isChecked && mentalCheckbox.isChecked && workHoursCheckbox.isChecked && managementCheckbox.isChecked
        editor.putBoolean("fatigueAllChecked", allCheckboxesSelected)
        editor.apply()
        intent = Intent(this, EmotionActivity::class.java) // Change to the next activity
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.imsafe_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Determine which menu option was selected
        return when (item.itemId) {
            R.id.home -> {
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
