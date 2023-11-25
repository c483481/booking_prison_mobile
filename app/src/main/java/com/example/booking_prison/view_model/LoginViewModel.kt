package com.example.booking_prison.view_model

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.LoginListener
import com.example.booking_prison.network.BodyLogin
import com.example.booking_prison.repository.AuthRepository

class LoginViewModel: ViewModel() {
    var username: String = ""
    var password: String =""
    lateinit var loginListener: LoginListener
    private val authRepository = AuthRepository()

    fun onClickLoginButton(view: View) {
        loginListener.onStarted()

        if(username.isNullOrEmpty() || password.isNullOrEmpty()) {
            loginListener.onNotValid()
            return
        }

        val result = authRepository.login(BodyLogin(username, password))
        loginListener.fallback(result)
    }

    fun onClickSingIn(view: View) {
        loginListener.onSingIn()
    }
}