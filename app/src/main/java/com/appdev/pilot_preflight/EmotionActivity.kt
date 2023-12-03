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
import android.widget.Toast

class EmotionActivity : AppCompatActivity() {
    private lateinit var continueHome: Button
    private lateinit var completeEvaluationButton: Button
    private lateinit var symptomCheckbox: CheckBox
    private lateinit var pastMealCheckbox: CheckBox
    private lateinit var futureMealCheckbox: CheckBox
    private lateinit var outlookCheckbox: CheckBox
    private lateinit var normalCheckbox: CheckBox

    private val prefsFileName = "MyPrefs" // Custom name for shared preferences file

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emotion)

        continueHome = findViewById(R.id.imsafe_continue_home_button)
        completeEvaluationButton = findViewById(R.id.complete_evaluation_button)

        symptomCheckbox = findViewById(R.id.emotion_symptom_checkbox)
        pastMealCheckbox = findViewById(R.id.emotion_past_meal)
        futureMealCheckbox = findViewById(R.id.emotion_future_meal)
        outlookCheckbox = findViewById(R.id.emotion_outlook)
        normalCheckbox = findViewById(R.id.emotion_normal)

        val prefs = getSharedPreferences(prefsFileName, Context.MODE_PRIVATE)

        symptomCheckbox.isChecked = prefs.getBoolean("emotion_symptom_checkbox", false)
        pastMealCheckbox.isChecked = prefs.getBoolean("emotion_past_meal", false)
        futureMealCheckbox.isChecked = prefs.getBoolean("emotion_future_meal", false)
        outlookCheckbox.isChecked = prefs.getBoolean("emotion_outlook", false)
        normalCheckbox.isChecked = prefs.getBoolean("emotion_normal", false)

        continueHome.setOnClickListener(this::continueHome)
        completeEvaluationButton.setOnClickListener(this::completeEvaluation)
    }

    private fun completeEvaluation(view: View) {
        val prefs = getSharedPreferences(prefsFileName, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean("emotion_symptom_checkbox", symptomCheckbox.isChecked)
        editor.putBoolean("emotion_past_meal", pastMealCheckbox.isChecked)
        editor.putBoolean("emotion_future_meal", futureMealCheckbox.isChecked)
        editor.putBoolean("emotion_outlook", outlookCheckbox.isChecked)
        editor.putBoolean("emotion_normal", normalCheckbox.isChecked)

        val allCheckboxesSelected =
            symptomCheckbox.isChecked && pastMealCheckbox.isChecked && futureMealCheckbox.isChecked && outlookCheckbox.isChecked && normalCheckbox.isChecked

        editor.putBoolean("emotionAllChecked", allCheckboxesSelected)
        editor.apply()
        intent = Intent(this, EvaluationResultsActivity::class.java) // Change to the next activity
        startActivity(intent)
    }

    private fun continueHome(view: View) {
        intent = Intent(this, MainActivity::class.java) // Change to the next activity
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
