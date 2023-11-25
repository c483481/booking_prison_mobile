package com.example.booking_prison.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.booking_prison.R
import com.example.booking_prison.databinding.ActivityBookingBinding
import com.example.booking_prison.listener.BookingListener
import com.example.booking_prison.network.BodyBooking
import com.example.booking_prison.utils.LoginUtils
import com.example.booking_prison.utils.hide
import com.example.booking_prison.utils.show
import com.example.booking_prison.utils.toast
import com.example.booking_prison.view_model.BookingViewModel
import java.text.SimpleDateFormat

@RequiresApi(Build.VERSION_CODES.N)
class BookingActivity : AppCompatActivity(), BookingListener {
    lateinit var binding: ActivityBookingBinding
    private val calendar = Calendar.getInstance()
    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("dd-MM-yyyy")

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_booking)
        setContentView(binding.root)

        binding.date.text = sdf.format(calendar.time)

        val model = ViewModelProvider(this)[BookingViewModel::class.java]

        val loginUtils = LoginUtils(this)

        model.loginUtils = loginUtils

        model.bookingListener = this

        model.selectedDate.value = calendar.time

        binding.model = model

        val session = arrayListOf("sesi 1", "sesi 2")

        model.selectedSesi.value = session[0]
        val adapterSession = ArrayAdapter(this, android.R.layout.simple_spinner_item, session)
        adapterSession.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.dropdownSesi.adapter = adapterSession

        binding.dropdownSesi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                val selectedSesi = binding.dropdownSesi.selectedItem.toString()
                model.selectedSesi.value = selectedSesi
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                toast("please select the items")
            }
        }

        val datePicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val date = sdf.format(calendar.time)

            binding.date.text = date

            model.selectedDate.value = calendar.time
        }

        binding.date.setOnClickListener {
            DatePickerDialog(this, datePicker, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    override fun onClick(data: LiveData<Boolean>) {
        data.observe(this, Observer {
            binding.loading.hide()
            if(it) {
                toast("booking success")
                val i = Intent(this, HomeActivity::class.java)
                startActivity(i)
                finish()
                return@Observer
            }

            toast("booking failed")
        })
    }

    override fun start() {
        binding.loading.show()
    }

    override fun notValid() {
        binding.loading.hide()
        toast("please fill the form")
    }

}