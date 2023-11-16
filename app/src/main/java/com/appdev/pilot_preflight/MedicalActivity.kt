package com.appdev.pilot_preflight

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MedicalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical)
        // Set up buttons that link to different websites
    }

    // Function to open the FAA website
    fun openFAAWebsite(view: android.view.View) {
        val url = "https://www.faa.gov/pilots"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    fun openPharm(view: android.view.View) {
        val url = "https://www.faa.gov/ame_guide/pharm"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    fun openDisease(view: android.view.View) {
        val url = "https://www.faa.gov/ame_guide/dec_cons/disease_prot/binocular"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    fun openSpecialIssuances(view: android.view.View) {
        val url = "https://www.faa.gov/ame_guide/special_iss"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    fun openHome(view: android.view.View) {
        val url = "https://www.faa.gov/ame_guide"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
