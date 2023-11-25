package com.example.booking_prison.listener

import androidx.lifecycle.LiveData

interface ProfileListener {
    fun logOut()

    fun started()

    fun onNotValid()

    fun onChangePassword(data: LiveData<Boolean>)
}