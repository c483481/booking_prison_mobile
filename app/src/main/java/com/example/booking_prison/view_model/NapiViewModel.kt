package com.example.booking_prison.view_model

import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.NapiListener
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

    fun onClickBack() {
        napiListener.onClickBack()
    }

    fun onClickAdd() {
        napiListener.onClickAdd()
    }
}