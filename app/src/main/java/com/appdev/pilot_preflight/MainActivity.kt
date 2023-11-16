package com.appdev.pilot_preflight

import android.content.Intent
import android.net.Uri
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
        val regulationsButton: Button = findViewById(R.id.regulationsButton)
        regulationsButton.setOnClickListener {
            startActivity(Intent(this, RegulationsActivity::class.java))
        }

        val medicalButton: Button = findViewById(R.id.medicalButton)
        medicalButton.setOnClickListener {
            startActivity(Intent(this, MedicalActivity::class.java))
        }
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var dateEditText: EditText
    private lateinit var calendarButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prior_evaluations)

        dateEditText = findViewById(R.id.dateEditText)
        calendarButton = findViewById(R.id.calendarButton)

        // Set the current date in the EditText by default
        val currentDate = getCurrentDate()
        dateEditText.setText(currentDate)
    }

    fun showDatePicker(view: View) {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                val selectedDate = "$day/${month + 1}/$year"
                dateEditText.setText(selectedDate)
            },
            currentYear,
            currentMonth,
            currentDay
        )

        datePickerDialog.show()
    }

    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        return "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
    }

    private fun checklistClick (view: View){
        Log.d(TAG, "checklistClick")
        intent = Intent(this, ImsafeActivity::class.java)
        startActivity(intent)


    }

}
