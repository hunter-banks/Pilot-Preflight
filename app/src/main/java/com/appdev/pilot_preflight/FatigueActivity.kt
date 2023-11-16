package com.appdev.pilot_preflight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button

class FatigueActivity : AppCompatActivity() {
    private lateinit var continueEmotion: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fatigue)
        setSupportActionBar(findViewById(R.id.fatigue_toolbar))
        continueEmotion = findViewById(R.id.imsafe_continue_emotion_button)
        continueEmotion.setOnClickListener(this::continueEmotionView)
    }
    private fun continueEmotionView(view: View) {
        intent = Intent(this, EmotionActivity::class.java)
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