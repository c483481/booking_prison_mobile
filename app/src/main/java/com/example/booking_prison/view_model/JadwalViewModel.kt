package com.example.booking_prison.view_model

import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.JadwalListener
import com.example.booking_prison.network.BodyJadwal
import com.example.booking_prison.repository.JadwalRepository
import com.example.booking_prison.utils.LoginUtils

class JadwalViewModel: ViewModel() {
    lateinit var jadwalListener: JadwalListener
    lateinit var loginUtils: LoginUtils
    private val jadwalRepository = JadwalRepository()
    fun fetchJadwal() {
        jadwalListener.onLoading()
        val data = jadwalRepository.getJadwal(loginUtils.getAccessToken())
        data.observeForever {
            jadwalListener.onClear()
            if(it == null) {
                jadwalListener.onFailed()
                return@observeForever
            }
            jadwalListener.onSuccess(it)
        }
    }

    fun changeJadwal(nama: String, hari: String) {
        if(nama.isNullOrEmpty()) {
            jadwalListener.onNotValid()
        }
        jadwalListener.onLoading()
        val data = jadwalRepository.changeJadwal(loginUtils.getAccessToken(), BodyJadwal(hari, nama))

        data.observeForever {
            jadwalListener.onClear()
            if(it == null) {
                jadwalListener.onFailed()
                return@observeForever
            }
            jadwalListener.onSuccesChange()
            jadwalListener.onSuccess(it)
        }
    }
}