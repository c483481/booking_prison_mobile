package com.example.booking_prison.view_model

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.CellListener
import com.example.booking_prison.utils.LoginUtils

class CellViewModel: ViewModel() {
    lateinit var loginUtils: LoginUtils
    lateinit var cellListener: CellListener

    fun onClickBackButton(view: View) {
        cellListener.onClickBack()
    }

    fun onClickAdd(view: View) {
        cellListener.onClickAdd()
    }

    fun fetchCell() {

    }
}