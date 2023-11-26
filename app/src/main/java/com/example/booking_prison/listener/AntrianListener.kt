package com.example.booking_prison.listener

import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.response.BookingResponse

interface AntrianListener {
    fun onFetchAntrian()
    fun onGetAntrianData(data: ResponseList<BookingResponse>?)
    fun onClickBack()
    fun onFailed()
    fun onSuccessPatch(xid: String)
    fun onClickClearAntrian()

    fun onSuccessClear()
}