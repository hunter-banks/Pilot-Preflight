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

class MedicationActivity : AppCompatActivity() {
    private lateinit var continueStress: Button
    private lateinit var verifyCheckbox: CheckBox
    private lateinit var impairCheckbox: CheckBox
    private lateinit var violateCheckbox: CheckBox
    private lateinit var informAMECheckbox: CheckBox

    private val prefsFileName = "MyPrefs" // Custom name for shared preferences file

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medication)

        continueStress = findViewById(R.id.imsafe_continue_stress_button)
        verifyCheckbox = findViewById(R.id.medication_verify_checkbox)
        impairCheckbox = findViewById(R.id.medication_impair)
        violateCheckbox = findViewById(R.id.medication_violate)
        informAMECheckbox = findViewById(R.id.medication_inform_ame)

        val prefs = getSharedPreferences(prefsFileName, Context.MODE_PRIVATE)

        verifyCheckbox.isChecked = prefs.getBoolean("medication_verify_checkbox", false)
        impairCheckbox.isChecked = prefs.getBoolean("medication_impair", false)
        violateCheckbox.isChecked = prefs.getBoolean("medication_violate", false)
        informAMECheckbox.isChecked = prefs.getBoolean("medication_inform_ame", false)

        continueStress.setOnClickListener(this::continueStress)
    }

    private fun continueStress(view: View) {
        val prefs = getSharedPreferences(prefsFileName, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("medication_verify_checkbox", verifyCheckbox.isChecked)
        editor.putBoolean("medication_impair", impairCheckbox.isChecked)
        editor.putBoolean("medication_violate", violateCheckbox.isChecked)
        editor.putBoolean("medication_inform_ame", informAMECheckbox.isChecked)

        val allCheckboxesSelected =
            verifyCheckbox.isChecked && impairCheckbox.isChecked && violateCheckbox.isChecked && informAMECheckbox.isChecked
        editor.putBoolean("medicationAllChecked", allCheckboxesSelected)
        editor.apply()
        intent = Intent(this, StressActivity::class.java) // Change to the next activity
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
