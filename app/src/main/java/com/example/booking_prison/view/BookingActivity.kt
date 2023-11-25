package com.example.booking_prison.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.booking_prison.R
import com.example.booking_prison.databinding.ActivityBookingBinding
import com.example.booking_prison.utils.toast
import java.text.SimpleDateFormat

@RequiresApi(Build.VERSION_CODES.N)
class BookingActivity : AppCompatActivity() {
    lateinit var binding: ActivityBookingBinding
    private val calendar = Calendar.getInstance()
    private lateinit var sesi: String
    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("dd-MM-yyyy")
    private val datePicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        binding.date.text = sdf.format(calendar.time)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_booking)
        setContentView(binding.root)

        binding.date.text = sdf.format(calendar.time)

        val session = arrayListOf("sesi 1", "sesi 2")
        val adapterSession = ArrayAdapter(this, android.R.layout.simple_spinner_item, session)
        adapterSession.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.dropdownSesi.adapter = adapterSession

        binding.date.setOnClickListener {
            DatePickerDialog(this, datePicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

}