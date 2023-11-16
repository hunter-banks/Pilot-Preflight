package com.appdev.pilot_preflight

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class PreviousEvaluations : AppCompatActivity() {

    private lateinit var dateEditText: EditText
    private lateinit var calendarButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prior_evaluations)

        dateEditText = findViewById(R.id.dateEditText)
        calendarButton = findViewById(R.id.calendarButton)

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
                val selectedDate = "${month + 1}/$day/$year"
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
        return "${calendar.get(Calendar.MONTH)}/${calendar.get(Calendar.DAY_OF_MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
    }
}