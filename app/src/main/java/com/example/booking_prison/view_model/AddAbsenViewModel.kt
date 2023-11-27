package com.example.booking_prison.view_model

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.AddAbsenListener
import com.example.booking_prison.repository.AbsenRepository
import com.example.booking_prison.repository.NapiRepository
import com.example.booking_prison.utils.LoginUtils

class AddAbsenViewModel: ViewModel() {
    lateinit var loginUtils: LoginUtils
    lateinit var addAbsenListener: AddAbsenListener
    private val napiRepository = NapiRepository()
    private val absenRepository = AbsenRepository()

    fun fetchNapi() {
        addAbsenListener.onLoading()

        val data = napiRepository.getListNapi(loginUtils.getAccessToken(), showAll = true)
        data.observeForever {
            addAbsenListener.clearLoading()
            if(it == null) {
                addAbsenListener.onFailed()
                return@observeForever
            }
            addAbsenListener.onSuccess(it)
        }
    }

    fun addAbsen(xid: String, tema: String) {
        addAbsenListener.onLoading()
        val data = absenRepository.postAddAbsen(loginUtils.getAccessToken(), xid, tema)
        data.observeForever {
            addAbsenListener.clearLoading()
            if(it) {
                addAbsenListener.onClickSuccessAdd()
                return@observeForever
            }
            addAbsenListener.onFailed()
        }
    }

    fun onClickBack(view: View) {
        addAbsenListener.onClickBack()
    }
}