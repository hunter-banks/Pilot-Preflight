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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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

        saveEvaluationToFile(resultsArray)


    }

    private fun saveEvaluationToFile(resultsArray: BooleanArray) {
        val timestamp = getCurrentTimestamp()
        val evaluationText = "$timestamp,${resultsArray.joinToString(separator = ",")}"

        try {
            // Open the file in append mode
            val fileOutputStream = openFileOutput("EvaluationReseults.txt", Context.MODE_APPEND)
            fileOutputStream.write(evaluationText.toByteArray())
            fileOutputStream.write("\n".toByteArray()) // Add a newline for each evaluation
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getCurrentTimestamp(): String {
        val dateFormat = SimpleDateFormat("MM-dd-yyyy hh:mm a", Locale.getDefault())
        val date = Date(System.currentTimeMillis())
        return dateFormat.format(date)
    }



    private fun setEvaluationResultText(resultsArray : BooleanArray)
    {

        var safeToFly = true
        for (result in resultsArray)
        {
            if (result == false)
            {
                safeToFly = false
            }
        }

        var displayedText = "Hello pilot, \n\n"



        if (safeToFly)
        {
            displayedText += "After reviewing your evaluation, we have determined that you are ready to fly per the IMSAFE guidelines. " +
                    "Please proceed with additional checks as needed and have a safe flight.\n\n"
        }
        else
        {
            displayedText += "After reviewing your evaluation, we have determined that you are currently unfit to fly. " +
                    "At this time we recommend that you review the Federal Aviation Administration's Guidelines and postpone your flight.\n\n"
        }
        displayedText += "Illness.............." + if (resultsArray[0]) "✅\n\n" else "❌\n\n"
        displayedText += "Medication......" + if (resultsArray[1]) "✅\n\n" else "❌\n\n"
        displayedText += "Stress.............." + if (resultsArray[2]) "✅\n\n" else "❌\n\n"
        displayedText += "Alcohol............" + if (resultsArray[3]) "✅\n\n" else "❌\n\n"
        displayedText += "Fatigue............" + if (resultsArray[4]) "✅\n\n" else "❌\n\n"
        displayedText += "Emotion..........." + if (resultsArray[5]) "✅\n\n" else "❌\n\n"

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
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
