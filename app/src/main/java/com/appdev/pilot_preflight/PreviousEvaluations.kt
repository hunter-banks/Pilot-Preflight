package com.appdev.pilot_preflight

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.InputStreamReader
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

        var evaluationsList = getEvaluationResults()
//        displayEvaluationResultsInTextView(evaluationsList)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val adapter = MyAdapter(evaluationsList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun displayEvaluationResultsInTextView(resultList: ArrayList<String>) {
        val textView: TextView = findViewById(R.id.test_text_view)

        // Clear existing text in the TextView
        textView.text = ""

        // Append each line from the ArrayList to the TextView
        for (line in resultList) {
            textView.append("$line\n")
        }
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

    fun reverseArrayList(inputList: ArrayList<String>): ArrayList<String> {
        val reversedList = inputList.clone() as ArrayList<String>
        reversedList.reverse()
        return reversedList
    }

    private fun getEvaluationResults(): ArrayList<String> {
        val resultList = ArrayList<String>()

        try {
            val fileInputStream = openFileInput("EvaluationReseults.txt")
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var line: String?

            while (bufferedReader.readLine().also { line = it } != null) {
                // Append each line to the ArrayList
                resultList.add(line.orEmpty())
            }

            // Close the streams
            bufferedReader.close()
            inputStreamReader.close()
            fileInputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return reverseArrayList(resultList)
    }


}