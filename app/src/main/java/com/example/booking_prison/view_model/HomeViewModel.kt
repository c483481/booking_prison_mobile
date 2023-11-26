package com.example.booking_prison.view_model

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.HomeListener
import com.example.booking_prison.repository.BookingRepository
import com.example.booking_prison.utils.LoginUtils

class HomeViewModel: ViewModel() {
    lateinit var homeListener: HomeListener
    lateinit var loginUtils: LoginUtils
    private val bookingRepository: BookingRepository = BookingRepository()


    fun onClickBookingButton(view: View) {
        homeListener.onClickBooking()
    }

    fun onClickCheckBooking(view: View) {
        homeListener.onFetchBooking()

        val result = bookingRepository.getListBooking(loginUtils.getAccessToken(), isUsers = true)

        result.observeForever {
            homeListener.onGetResult(it)
        }
    }
}