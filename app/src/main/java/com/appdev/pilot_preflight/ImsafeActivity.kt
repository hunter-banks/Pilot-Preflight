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

class ImsafeActivity : AppCompatActivity() {
    var TAG = "ImsafeActivity"

    private lateinit var illnessButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imsafe)
        illnessButton = findViewById(R.id.imsafe_continue_illness_button)
        illnessButton.setOnClickListener(this::continueIllness)

    }

    private fun continueIllness(view: View) {
        val prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        prefs.edit().clear().apply()

        val intent = Intent(this, IllnessActivity::class.java)
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
