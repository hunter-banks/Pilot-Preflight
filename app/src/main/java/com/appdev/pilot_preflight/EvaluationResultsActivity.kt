package com.appdev.pilot_preflight

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class EvaluationResultsActivity : AppCompatActivity() {
    var TAG = "ImsafeActivity"

    private lateinit var home: Button
    private lateinit var results_text_view : TextView

    private val prefsFileName = "MyPrefs" // Custom name for shared preferences file


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluation_result)
        home = findViewById(R.id.return_home_button)
        home.setOnClickListener(this::returnHome)

        results_text_view = findViewById(R.id.detailed_results_text_area)

        val prefs = getSharedPreferences(prefsFileName, Context.MODE_PRIVATE)

        val resultsArray = BooleanArray(6)
        resultsArray[0] = prefs.getBoolean("illnessAllChecked", false)
        resultsArray[1] = prefs.getBoolean("medicationAllChecked", false)
        resultsArray[2] = prefs.getBoolean("stressAllChecked", false)
        resultsArray[3] = prefs.getBoolean("alcoholAllChecked", false)
        resultsArray[4] = prefs.getBoolean("fatigueAllChecked", false)
        resultsArray[5] = prefs.getBoolean("emotionAllChecked", false)
        setEvaluationResultText(resultsArray)
    }


    private fun setEvaluationResultText(resultsArray : BooleanArray)
    {
//        val toastMessage = "Results Array: ${resultsArray.joinToString(", ")}"
//        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()

        var safeToFly = true
        for (result in resultsArray)
        {
            //Toast.makeText(this, "Result: $result", Toast.LENGTH_SHORT).show()
            if (result == false)
            {
//                Toast.makeText(this, "FALSE DETECTED", Toast.LENGTH_SHORT).show()
                safeToFly = false
            }
        }

        var displayedText = "Hello pilot, \n"

        Toast.makeText(this, "Safe to Fly: $safeToFly", Toast.LENGTH_SHORT).show()


        if (!safeToFly)
        {
            displayedText += "After reviewing your evaluation we have determined that you are currently unfit to fly. " +
                    "At this time we recommend that you review the Federal Aviation Administration's Guidelines and postpone your flight."
        }
        else
        {
            displayedText += "After reviewing your evaluation we have determined that you are ready to fly per the FAA IMSAFE guidelines. " +
                    "Please proceed with additional checks as needed and have a safe flight."
        }

        results_text_view.setText(displayedText)

    }

    private fun returnHome(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d(TAG, "inflate")
        menuInflater.inflate(R.menu.imsafe_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Determine which menu option was selected
        return when (item.itemId) {
            R.id.home -> {
                // Add selected
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
