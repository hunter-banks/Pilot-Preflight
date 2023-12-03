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

class AlcoholActivity : AppCompatActivity() {
    private lateinit var continueFatigue: Button
    private lateinit var consumeCheckbox: CheckBox
    private lateinit var bacCheckbox: CheckBox
    private lateinit var hydrateCheckbox: CheckBox
    private lateinit var hangoverCheckbox: CheckBox

    private val prefsFileName = "MyPrefs" // Custom name for shared preferences file

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alcohol)

        continueFatigue = findViewById(R.id.imsafe_continue_fatigue_button)
        consumeCheckbox = findViewById(R.id.alcohol_consume_checkbox)
        bacCheckbox = findViewById(R.id.alcohol_bac)
        hydrateCheckbox = findViewById(R.id.alcohol_hydrate)
        hangoverCheckbox = findViewById(R.id.alcohol_hangover)

        val prefs = getSharedPreferences(prefsFileName, Context.MODE_PRIVATE)

        consumeCheckbox.isChecked = prefs.getBoolean("alcohol_consume_checkbox", false)
        bacCheckbox.isChecked = prefs.getBoolean("alcohol_bac", false)
        hydrateCheckbox.isChecked = prefs.getBoolean("alcohol_hydrate", false)
        hangoverCheckbox.isChecked = prefs.getBoolean("alcohol_hangover", false)

        continueFatigue.setOnClickListener(this::continueFatigue)
    }

    private fun continueFatigue(view: View) {
        val prefs = getSharedPreferences(prefsFileName, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("alcohol_consume_checkbox", consumeCheckbox.isChecked)
        editor.putBoolean("alcohol_bac", bacCheckbox.isChecked)
        editor.putBoolean("alcohol_hydrate", hydrateCheckbox.isChecked)
        editor.putBoolean("alcohol_hangover", hangoverCheckbox.isChecked)

        val allCheckboxesSelected =
            consumeCheckbox.isChecked && bacCheckbox.isChecked && hydrateCheckbox.isChecked && hangoverCheckbox.isChecked
        editor.putBoolean("alcoholAllChecked", allCheckboxesSelected)
        editor.apply()
        intent = Intent(this, FatigueActivity::class.java) // Change to the next activity
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
