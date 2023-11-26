package com.example.booking_prison.listener

import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.response.CellResponse

interface CellListener {
    fun onClickBack()
    fun onClickAdd()
    fun onFetch()
    fun onFailed()
    fun onSuccessFetch(data: ResponseList<CellResponse>)
}