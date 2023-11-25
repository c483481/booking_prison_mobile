package com.example.booking_prison.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asisten_damkar.response.Response
import com.example.booking_prison.network.BodyBooking
import com.example.booking_prison.network.BookingNetwork
import retrofit2.Call
import retrofit2.Callback

class BookingRepository {
    private val bookingNetwork = BookingNetwork()

    fun createBooking(token: String, payload: BodyBooking): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()

        bookingNetwork.postCreateBooking(token, payload).enqueue(object : Callback<Response<Any>> {
            override fun onResponse(
                call: Call<Response<Any>>,
                response: retrofit2.Response<Response<Any>>
            ) {
                data.value = response.isSuccessful
            }

            override fun onFailure(call: Call<Response<Any>>, t: Throwable) {
                data.value = false
            }
        })

        return data
    }
}