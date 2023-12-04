package com.appdev.pilot_preflight

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
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
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private var evaluationsList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prior_evaluations)

        dateEditText = findViewById(R.id.dateEditText)
        calendarButton = findViewById(R.id.calendarButton)
        recyclerView = findViewById(R.id.recyclerView)

        // Initialize the adapter with the full list
        evaluationsList = getEvaluationResults()
        adapter = MyAdapter(evaluationsList)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        dateEditText.setText(getCurrentDate())

        dateEditText.setOnClickListener {
            dateEditText.text.clear()
        }

        // Add a TextWatcher to the EditText for real-time search
        dateEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed, but required to implement
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                // Apply the filter as the user types
                adapter.filter.filter(charSequence)
            }

            override fun afterTextChanged(editable: Editable?) {
                // Not needed, but required to implement
            }
        })
    }

    fun showDatePicker(view: View) {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                // Format the day part to always have two digits
                val formattedDay = String.format("%02d", day)
                val selectedDate = "${month + 1}/$formattedDay/$year"
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
            val fileInputStream = openFileInput("EvaluationResults.txt")
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
