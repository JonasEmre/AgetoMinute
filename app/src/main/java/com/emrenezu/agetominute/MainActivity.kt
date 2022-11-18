package com.emrenezu.agetominute

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
    private fun dateHandler(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)

        DatePickerDialog(this,
            { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                tvSelectedDate.text = "${selectedDayOfMonth}/${selectedMonth+1}/$selectedYear"
            }, year, month, day
        ).show()
    }
}