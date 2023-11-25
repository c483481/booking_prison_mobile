package com.example.booking_prison.view_model

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.BookingListener
import com.example.booking_prison.network.BodyBooking
import com.example.booking_prison.repository.BookingRepository
import com.example.booking_prison.utils.LoginUtils
import com.example.booking_prison.utils.dateToIsoString
import java.util.Date

class BookingViewModel: ViewModel() {
    var name: String = ""
    var noTelp: String = ""
    var noKtp: String = ""
    var alamat: String = ""
    var barang: String = ""
    val selectedSesi = MutableLiveData<String>()
    val selectedDate = MutableLiveData<Date>()
    lateinit var sesi: String
    lateinit var date: Date
    lateinit var bookingListener: BookingListener
    lateinit var loginUtils: LoginUtils
    private val bookingRepository = BookingRepository()
    init {
        // Amati perubahan nilai selectedSesi
        selectedSesi.observeForever { newValue ->
            sesi = newValue
        }

        selectedDate.observeForever{
            date = it
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun onClickBooking(view: View) {
        bookingListener.start()
        if(name.isEmpty() || noTelp.isEmpty() || noKtp.isEmpty() || alamat.isEmpty() || alamat.isEmpty() || barang.isEmpty()) {
            bookingListener.notValid()
            return
        }

        val pickDate = dateToIsoString(date)
        val payload = BodyBooking(
            name = name,
            noKtp = noKtp,
            noTelp = noTelp,
            alamat = alamat,
            sesi = sesi,
            barang = barang,
            date = pickDate
        )

        val result = bookingRepository.createBooking(loginUtils.getAccessToken(), payload)
        bookingListener.onClick(result)
    }
}