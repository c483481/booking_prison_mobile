package com.example.booking_prison.listener

import androidx.lifecycle.LiveData
import com.example.booking_prison.network.BodyBooking

interface BookingListener {
    fun onClick(data: LiveData<Boolean>)

    fun start()

    fun notValid()

    fun onBack()
}