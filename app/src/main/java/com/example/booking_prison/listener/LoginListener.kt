package com.example.booking_prison.listener

import androidx.lifecycle.LiveData
import com.example.booking_prison.response.LoginResponse

interface LoginListener {
    fun onStarted()

    fun onNotValid()

    fun fallback(data: LiveData<LoginResponse?>)

    fun onSingIn()
}