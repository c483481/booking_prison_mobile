package com.example.booking_prison.view_model

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.ActivitasListener

class AktivitasViewModel: ViewModel() {
    lateinit var activitasListener: ActivitasListener
    fun onCLickIbadah(view : View) {
        activitasListener.onClickList("ibadah")
    }

    fun onClickOlahraga(view: View) {
        activitasListener.onClickList("olahraga")
    }

    fun onClickSenam(view: View) {
        activitasListener.onClickList("senam")
    }

    fun onClickCleaning(view: View) {
        activitasListener.onClickList("cleaning")
    }

    fun onClickBackButton(view: View) {
        activitasListener.onClickBackButton()
    }
}