package com.example.booking_prison.listener

import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.response.NapiResponse

interface NapiListener {
    fun onFetch()
    fun onSuccessFetch(data: ResponseList<NapiResponse>)
    fun onFailed()
    fun onClickBack()
    fun onClickAdd()
}