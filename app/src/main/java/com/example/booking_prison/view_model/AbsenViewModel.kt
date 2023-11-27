package com.example.booking_prison.view_model

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.AbsenListener
import com.example.booking_prison.repository.AbsenRepository
import com.example.booking_prison.utils.LoginUtils

class AbsenViewModel: ViewModel() {
    lateinit var absenListener: AbsenListener
    lateinit var loginUtils: LoginUtils
    private val absenRepository = AbsenRepository()
    fun fetch(tema: String) {
        absenListener.onLoading()
        val data = absenRepository.getListAbsen(loginUtils.getAccessToken(), showAll = true, tema = tema)
        data.observeForever {
            if(it == null) {
                absenListener.onFailed()
                return@observeForever
            }
            absenListener.onSuccess(it)
        }
    }

    fun onClickAdd(view: View) {
        absenListener.onClickAdd()
    }

    fun onClickBack(view: View) {
        absenListener.onClickBack()
    }
}