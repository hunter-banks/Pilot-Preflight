package com.appdev.pilot_preflight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button

class AlcoholActivity : AppCompatActivity() {
    private lateinit var continueFatigue:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alcohol)
        continueFatigue = findViewById(R.id.imsafe_continue_fatigue_button)
        continueFatigue.setOnClickListener(this::continueFatigue)
    }
    private fun continueFatigue(view: View) {
        intent = Intent(this, FatigueActivity::class.java)
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
                // Add selected
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}