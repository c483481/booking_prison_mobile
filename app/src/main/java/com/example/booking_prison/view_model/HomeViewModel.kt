package com.example.booking_prison.view_model

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.HomeListener

class HomeViewModel: ViewModel() {
    lateinit var homeListener: HomeListener

    fun onClickBookingButton(view: View) {
        homeListener.onClickBooking()
    }
}