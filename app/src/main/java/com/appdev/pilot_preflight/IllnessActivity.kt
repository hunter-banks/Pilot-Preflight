package com.appdev.pilot_preflight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button

class IllnessActivity : AppCompatActivity() {
    private lateinit var continueMed:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_illness)
        setSupportActionBar(findViewById(R.id.illness_toolbar))
        continueMed = findViewById(R.id.imsafe_continue_med_button)
        continueMed.setOnClickListener(this::continueMedication)
    }
    private fun continueMedication(view: View) {
        intent = Intent(this, MedicationActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
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