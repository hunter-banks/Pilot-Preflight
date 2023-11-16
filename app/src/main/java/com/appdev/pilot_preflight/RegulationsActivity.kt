package com.appdev.pilot_preflight

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class RegulationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regulations)
        // Configure the layout elements for the regulations screen
        // Set text for title and information box

        fun openRegulations(view: View) {
            val url = "https://www.faa.gov/regulations_policies"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

    }
}
