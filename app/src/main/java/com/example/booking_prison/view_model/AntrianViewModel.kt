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
        val result = bookingRepository
            .getListBooking(loginUtils.getAccessToken(), showAll = true, notClear = true, today = true)

        result.observeForever {
            antrianListener.onGetAntrianData(it)
        }
    }

    fun onClickBackButton(view: View)  {
        antrianListener.onClickBack()
    }

    fun updateStatusBooking(xid: String) {
        antrianListener.onFetchAntrian()
        val result = bookingRepository.patchBookingStatus(loginUtils.getAccessToken(), xid)
        result.observeForever {
            if(it) {
                antrianListener.onSuccessPatch(xid)
                return@observeForever
            }
            antrianListener.onFailed()
        }
    }

    fun onClickClearAll(view: View) {
        antrianListener.onClickClearAntrian()
    }

    fun clearAll() {
        antrianListener.onFetchAntrian()

        val result = bookingRepository.updateAllStatusToday(loginUtils.getAccessToken())
        result.observeForever {
            if(it) {
                antrianListener.onSuccessClear()
                return@observeForever
            }
            antrianListener.onFailed()
        }
    }
}