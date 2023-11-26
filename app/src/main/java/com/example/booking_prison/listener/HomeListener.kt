package com.example.booking_prison.listener

import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.response.BookingResponse

interface HomeListener {
    fun onClickBooking()

    fun onFetchBooking()

    fun onGetResult(data: ResponseList<BookingResponse>?)
}