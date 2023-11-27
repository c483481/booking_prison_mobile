package com.example.booking_prison.listener

import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.response.NapiResponse

interface AddAbsenListener {
    fun onLoading()
    fun clearLoading()
    fun onFailed()
    fun onSuccess(data: ResponseList<NapiResponse>)
    fun onClickSuccessAdd()
    fun onClickBack()
}