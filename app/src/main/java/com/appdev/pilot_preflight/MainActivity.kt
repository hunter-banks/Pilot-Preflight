package com.appdev.pilot_preflight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    private lateinit var checklistButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checklistButton = findViewById(R.id.start_checklist_button)
        checklistButton.setOnClickListener(this::checklistClick)
    }

    private fun checklistClick (view: View){
        Log.d(TAG, "checklistClick")
        intent = Intent(this, ImsafeActivity::class.java)
        startActivity(intent)
    }
}