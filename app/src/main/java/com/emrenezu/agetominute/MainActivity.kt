package com.emrenezu.agetominute

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Select date button logic.
        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
            dateHandler()
        }
    }

    // Method that gives a functionality for btnDatePicker.
    private fun dateHandler() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)

        val datePickerDialog = DatePickerDialog(
            this,
            // When selecting a date from dialog menu, this block will work.
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "${selectedDayOfMonth}/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate.text = selectedDate
                // Converting the date to simple and readable format.
                val simpleDateFormat = SimpleDateFormat("dd/mm/yyyy", Locale.US)
                val theDate = simpleDateFormat.parse(selectedDate)

                // Converting time to minutes format
                val dateToMinutes = theDate?.time?.div(60000)

                // Access the current date of system
                val currentDate = simpleDateFormat.parse(
                    simpleDateFormat.format(System.currentTimeMillis())
                )
                val currentDateToMinutes = currentDate?.time?.div(60000)

                // Finding the difference between selected date and now.
                val differenceInMinutes = dateToMinutes?.let { currentDateToMinutes?.minus(it) }

                // Assign the result and show
                val tvResult = findViewById<TextView>(R.id.tvResult)
                tvResult.text = differenceInMinutes.toString()

            }, year, month, day
        )

        // In dialog view there will be limit for max date
        datePickerDialog.datePicker.maxDate =
            System.currentTimeMillis() - 86400000 // Means yesterday
        datePickerDialog.show()
    }
}