package com.appdev.pilot_preflight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val regulationsButton: Button = findViewById(R.id.regulationsButton)
        regulationsButton.setOnClickListener {
            startActivity(Intent(this, RegulationsActivity::class.java))
        }

        val medicalButton: Button = findViewById(R.id.medicalButton)
        medicalButton.setOnClickListener {
            startActivity(Intent(this, MedicalActivity::class.java))
        }
    }
}
