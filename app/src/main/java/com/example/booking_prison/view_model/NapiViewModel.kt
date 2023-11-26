package com.example.booking_prison.view_model

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.NapiListener
import com.example.booking_prison.network.BodyNapi
import com.example.booking_prison.repository.NapiRepository
import com.example.booking_prison.utils.LoginUtils

class NapiViewModel: ViewModel() {
    lateinit var loginUtils: LoginUtils
    lateinit var cellName: String
    lateinit var napiListener: NapiListener
    private val napiRepository = NapiRepository()
    fun fetchNapi() {
        napiListener.onFetch()
        val data = napiRepository.getListNapi(loginUtils.getAccessToken(), cellName = cellName)

        data.observeForever {
            if(it == null) {
                napiListener.onFailed()
                return@observeForever
            }
            napiListener.onSuccessFetch(it)
        }
    }

    fun addNapi(name: String, reason: String, longTime: String, cellXid: String) {
        napiListener.onFetch()
        if(name.isNullOrEmpty() || reason.isNullOrEmpty() || longTime.isNullOrEmpty() || longTime.toInt() <= 0)  {
            napiListener.onNotValid()
            return
        }

        val payload = BodyNapi(name, longTime.toInt(), reason, cellXid)
        val result = napiRepository.postAddNapi(loginUtils.getAccessToken(), payload)

        result.observeForever {
            if(it) {
                napiListener.onSuccessAdd()
                return@observeForever
            }
            napiListener.onFailed()
        }
    }

    fun onClickBack(view: View) {
        napiListener.onClickBack()
    }

    fun onClickAdd(view: View) {
        napiListener.onClickAdd()
    }
}