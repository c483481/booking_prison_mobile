package com.example.booking_prison.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.booking_prison.R
import com.example.booking_prison.databinding.FragmentHomeBinding
import com.example.booking_prison.listener.HomeListener
import com.example.booking_prison.view_model.HomeViewModel
import java.text.SimpleDateFormat

class HomeFragment : Fragment(), HomeListener {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        val model = ViewModelProvider(this)[HomeViewModel::class.java]

        model.homeListener = this

        binding.model = model

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClickBooking() {
        val i = Intent(context, BookingActivity::class.java)
        startActivity(i)
    }
}