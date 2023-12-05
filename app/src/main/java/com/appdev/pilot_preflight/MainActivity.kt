package com.appdev.pilot_preflight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    private lateinit var checklistButton: Button
    private lateinit var pastResult: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checklistButton = findViewById(R.id.start_checklist_button)
        checklistButton.setOnClickListener(this::checklistClick)
        pastResult = findViewById(R.id.display)
        pastResult.setOnClickListener(this::pastResultClick)
        val regulationsButton: Button = findViewById(R.id.regulationsButton)
        regulationsButton.setOnClickListener {
            startActivity(Intent(this, RegulationsActivity::class.java))
        }


        val medicalButton: Button = findViewById(R.id.medicalButton)
        medicalButton.setOnClickListener {
            startActivity(Intent(this, MedicalActivity::class.java))
        }
    }

    private fun checklistClick (view: View){
        Log.d(TAG, "checklistClick")
        intent = Intent(this, ImsafeActivity::class.java)
        startActivity(intent)
    }
    private fun pastResultClick(view: View) {
        intent = Intent(this, PreviousEvaluations::class.java)
        startActivity(intent)
    }

}
