package com.example.booking_prison.view_model

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.AddCellListener
import com.example.booking_prison.network.BodyCell
import com.example.booking_prison.repository.CellRepository
import com.example.booking_prison.utils.LoginUtils

class AddCellViewModel: ViewModel() {
    lateinit var loginUtils: LoginUtils
    lateinit var addCellListener: AddCellListener
    private val cellRepository = CellRepository()
    var name: String = ""
    var max: String = ""

    fun onClickBack(view: View) {
        addCellListener.onClickBack()
    }

    fun onClickAdd(view: View) {
        if(name.isEmpty() || max.isEmpty() || max.toInt() < 0) {
            addCellListener.onNotValid()
            return
        }

        val data = BodyCell(name, max.toInt())
        val result = cellRepository.addCell(loginUtils.getAccessToken(), data)

        result.observeForever {
            if(it) {
                addCellListener.onSuccess()
                return@observeForever
            }
            addCellListener.onFailed()
        }
    }
}