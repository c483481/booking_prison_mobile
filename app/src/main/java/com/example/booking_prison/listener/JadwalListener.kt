package com.example.booking_prison.listener

import com.example.booking_prison.response.JadwalResponse

interface JadwalListener {
    fun onLoading()
    fun onClear()
    fun onSuccesChange()
    fun onFailed()
    fun onSuccess(data: JadwalResponse)
    fun onClickBackButton()
    fun onNotValid()
}