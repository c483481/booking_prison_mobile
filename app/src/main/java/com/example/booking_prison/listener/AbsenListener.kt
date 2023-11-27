package com.example.booking_prison.listener

import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.response.AbsenResponse

interface AbsenListener {
    fun onLoading()
    fun onFailed()
    fun onSuccess(data: ResponseList<AbsenResponse>)
    fun onClickBack()
    fun onClickAdd()
}