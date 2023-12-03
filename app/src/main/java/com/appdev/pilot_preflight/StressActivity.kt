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

class StressActivity : AppCompatActivity() {
    private lateinit var continueAlcohol: Button
    private lateinit var symptomCheckbox: CheckBox
    private lateinit var impairCheckbox: CheckBox
    private lateinit var recentEventCheckbox: CheckBox
    private lateinit var copingCheckbox: CheckBox
    private lateinit var unresolvedCheckbox: CheckBox

    private val prefsFileName = "MyPrefs" // Custom name for shared preferences file

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stress)

        continueAlcohol = findViewById(R.id.imsafe_continue_alc_button)
        symptomCheckbox = findViewById(R.id.stress_symptom_checkbox)
        impairCheckbox = findViewById(R.id.stress_impair_checkbox)
        recentEventCheckbox = findViewById(R.id.stress_recent_event_checkbox)
        copingCheckbox = findViewById(R.id.stress_coping_checkbox)
        unresolvedCheckbox = findViewById(R.id.stress_unresolved_checkbox)

        val prefs = getSharedPreferences(prefsFileName, Context.MODE_PRIVATE)

        symptomCheckbox.isChecked = prefs.getBoolean("stress_symptom_checkbox", false)
        impairCheckbox.isChecked = prefs.getBoolean("stress_impair_checkbox", false)
        recentEventCheckbox.isChecked = prefs.getBoolean("stress_recent_event_checkbox", false)
        copingCheckbox.isChecked = prefs.getBoolean("stress_coping_checkbox", false)
        unresolvedCheckbox.isChecked = prefs.getBoolean("stress_unresolved_checkbox", false)

        continueAlcohol.setOnClickListener(this::continueAlcohol)
    }

    private fun continueAlcohol(view: View) {
        val prefs = getSharedPreferences(prefsFileName, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("stress_symptom_checkbox", symptomCheckbox.isChecked)
        editor.putBoolean("stress_impair_checkbox", impairCheckbox.isChecked)
        editor.putBoolean("stress_recent_event_checkbox", recentEventCheckbox.isChecked)
        editor.putBoolean("stress_coping_checkbox", copingCheckbox.isChecked)
        editor.putBoolean("stress_unresolved_checkbox", unresolvedCheckbox.isChecked)

        val allCheckboxesSelected =
            symptomCheckbox.isChecked && impairCheckbox.isChecked && recentEventCheckbox.isChecked && copingCheckbox.isChecked && unresolvedCheckbox.isChecked
        editor.putBoolean("stressAllChecked", allCheckboxesSelected)
        editor.apply()
        intent = Intent(this, AlcoholActivity::class.java) // Change to the next activity
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
