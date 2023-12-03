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

class IllnessActivity : AppCompatActivity() {
    private lateinit var continueMed: Button
    private lateinit var symptomCheckbox: CheckBox
    private lateinit var impairCheckbox: CheckBox
    private lateinit var recentProcCheckbox: CheckBox
    private lateinit var allergiesCheckbox: CheckBox

    private val prefsFileName = "MyPrefs" // Custom name for shared preferences file

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_illness)

        continueMed = findViewById(R.id.imsafe_continue_med_button)
        symptomCheckbox = findViewById(R.id.illness_symptom_checkbox)
        impairCheckbox = findViewById(R.id.illness_impair)
        recentProcCheckbox = findViewById(R.id.illness_recent_proc)
        allergiesCheckbox = findViewById(R.id.illness_allergies)

        val prefs = getSharedPreferences(prefsFileName, Context.MODE_PRIVATE)

        symptomCheckbox.isChecked = prefs.getBoolean("symptomCheckbox", false)
        impairCheckbox.isChecked = prefs.getBoolean("impairCheckbox", false)
        recentProcCheckbox.isChecked = prefs.getBoolean("recentProcCheckbox", false)
        allergiesCheckbox.isChecked = prefs.getBoolean("allergiesCheckbox", false)

        continueMed.setOnClickListener(this::continueMedication)
    }

    private fun continueMedication(view: View) {
        val prefs = getSharedPreferences(prefsFileName, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("symptomCheckbox", symptomCheckbox.isChecked)
        editor.putBoolean("impairCheckbox", impairCheckbox.isChecked)
        editor.putBoolean("recentProcCheckbox", recentProcCheckbox.isChecked)
        editor.putBoolean("allergiesCheckbox", allergiesCheckbox.isChecked)

        val allCheckboxesSelected =
            symptomCheckbox.isChecked && impairCheckbox.isChecked && recentProcCheckbox.isChecked && allergiesCheckbox.isChecked
        editor.putBoolean("illnessAllChecked", allCheckboxesSelected)
        editor.apply()
        intent = Intent(this, MedicationActivity::class.java)
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
