package com.example.booking_prison.view_model

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.AntrianListener
import com.example.booking_prison.repository.BookingRepository
import com.example.booking_prison.utils.LoginUtils

class AntrianViewModel: ViewModel() {
    lateinit var antrianListener: AntrianListener
    lateinit var loginUtils: LoginUtils
    private val bookingRepository = BookingRepository()
    fun fetchData() {
        antrianListener.onFetchAntrian()
        val result = bookingRepository.getListBooking(loginUtils.getAccessToken(), showAll = true, notClear = true)

        result.observeForever {
            antrianListener.onGetAntrianData(it)
        }
    }

    fun onClickBackButton(view: View)  {
        antrianListener.onClickBack()
    }
}