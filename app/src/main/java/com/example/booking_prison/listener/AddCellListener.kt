package com.example.booking_prison.listener

interface AddCellListener {
    fun onLoading()
    fun onFailed()
    fun onSuccess()
    fun onClickBack()
    fun onNotValid()
}