package com.example.booking_prison.view_model

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.CellListener
import com.example.booking_prison.repository.CellRepository
import com.example.booking_prison.utils.LoginUtils

class CellViewModel: ViewModel() {
    lateinit var loginUtils: LoginUtils
    lateinit var cellListener: CellListener
    private val cellRepository = CellRepository()

    fun onClickBackButton(view: View) {
        cellListener.onClickBack()
    }

    fun onClickAdd(view: View) {
        cellListener.onClickAdd()
    }

    fun fetchCell() {
        cellListener.onFetch()

        val data = cellRepository.getListCell(loginUtils.getAccessToken())

        data.observeForever {
            if(it == null) {
                cellListener.onFailed()
                return@observeForever
            }
            cellListener.onSuccessFetch(it)
        }
    }
}