package com.example.booking_prison.view_model

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.ProfileListener
import com.example.booking_prison.network.BodyChangePassword
import com.example.booking_prison.network.BodyRegister
import com.example.booking_prison.repository.UsersRepository
import com.example.booking_prison.utils.LoginUtils

class ProfileViewModel: ViewModel() {
    var password: String = ""
    lateinit var profileListener: ProfileListener
    lateinit var loginUtils: LoginUtils
    private val usersRepository = UsersRepository()


    fun onClickChangePassword(view: View) {
        profileListener.started()

        if(password.isNullOrEmpty()) {
            profileListener.onNotValid()
            return
        }

        val data = usersRepository.changeLogin(loginUtils.getAccessToken(), BodyChangePassword(password))

        profileListener.onChangePassword(data)
    }

    fun onClickLogoutButton(view: View) {
        profileListener.logOut()
    }
}