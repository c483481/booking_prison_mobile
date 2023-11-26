package com.example.booking_prison.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.R
import com.example.booking_prison.databinding.FragmentHomeBinding
import com.example.booking_prison.listener.HomeListener
import com.example.booking_prison.response.BookingResponse
import com.example.booking_prison.utils.LoginUtils
import com.example.booking_prison.utils.hide
import com.example.booking_prison.utils.show
import com.example.booking_prison.view_model.HomeViewModel
import java.text.SimpleDateFormat

class HomeFragment : Fragment(), HomeListener {
    lateinit var binding: FragmentHomeBinding
    lateinit var mdialog: Dialog
    lateinit var loading: ProgressBar
    lateinit var textEmpty: TextView
    lateinit var scrollView: ScrollView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        val model = ViewModelProvider(this)[HomeViewModel::class.java]

        model.loginUtils = LoginUtils(container!!.context)

        mdialog = Dialog(container.context)

        mdialog.setContentView(R.layout.pop_up_booking_card)

        textEmpty = mdialog.findViewById(R.id.pop_up_data_empty)

        loading = mdialog.findViewById(R.id.pop_up_loading)

        scrollView = mdialog.findViewById(R.id.pop_up_scroll_view)

        mdialog.findViewById<Button>(R.id.pop_up_button).setOnClickListener {
            mdialog.dismiss()
        }

        model.homeListener = this

        binding.model = model

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClickBooking() {
        val i = Intent(context, BookingActivity::class.java)
        startActivity(i)
    }

    override fun onFetchBooking() {
        mdialog.show()
        loading.show()
    }

    override fun onGetResult(data: ResponseList<BookingResponse>?) {
        loading.hide()
        if(data == null) {
            Toast.makeText(context, "Something wrong, please try again later", Toast.LENGTH_LONG).show()
            return
        }
        if(data.count == 0) {
            textEmpty.visibility = View.VISIBLE
            return
        }

        scrollView.visibility = View.VISIBLE

        Toast.makeText(context, "data ada", Toast.LENGTH_LONG).show()
    }
}