package com.example.booking_prison.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asisten_damkar.response.Response
import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.network.BodyBooking
import com.example.booking_prison.network.BookingNetwork
import com.example.booking_prison.response.BookingResponse
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

    fun getListBooking(token: String, isUsers: Boolean? = null, limit: Int = 10, showAll: Boolean = false, notClear: Boolean? = null): LiveData<ResponseList<BookingResponse>?> {
        val data = MutableLiveData<ResponseList<BookingResponse>?>()

        bookingNetwork
            .getListBooking(token, isUsers = isUsers, limit = limit, showAll = showAll, noClear = notClear)
            .enqueue(object : Callback<Response<ResponseList<BookingResponse>>> {
                override fun onResponse(
                    call: Call<Response<ResponseList<BookingResponse>>>,
                    response: retrofit2.Response<Response<ResponseList<BookingResponse>>>
                ) {
                    if(response.isSuccessful) {
                        data.value = response.body()!!.data
                        return
                    }

                    data.value = null
                }

                override fun onFailure(
                    call: Call<Response<ResponseList<BookingResponse>>>,
                    t: Throwable
                ) {
                    data.value = null
                }

            })

        return data
    }
}