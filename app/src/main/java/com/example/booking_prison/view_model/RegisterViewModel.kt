package com.example.booking_prison.view_model

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.RegisterListener
import com.example.booking_prison.network.BodyRegister
import com.example.booking_prison.repository.AuthRepository

class RegisterViewModel: ViewModel() {
    var username: String = ""
    var password: String = ""
    private val authRepository = AuthRepository()
    lateinit var registerListener: RegisterListener

    fun onClickRegisterButton(view: View) {
        registerListener.onStarted()

        if(username.isNullOrEmpty() || password.isNullOrEmpty()) {
            registerListener.onNotValid()
            return
        }

        val data = authRepository.registerUser(BodyRegister(username, password))

        registerListener.fallback(data)
    }

    fun onClickSingUp(view: View) {
        registerListener.onSingUp()
    }
}