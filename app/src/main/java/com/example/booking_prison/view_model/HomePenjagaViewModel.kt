package com.example.booking_prison.view_model

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.booking_prison.listener.HomePenjagaListener

class HomePenjagaViewModel: ViewModel() {
    lateinit var homePenjagaListener: HomePenjagaListener

    fun onClickAntrian(view: View) {
        homePenjagaListener.onClickAntrtian()
    }

    fun onClickActivitas(view: View) {
        homePenjagaListener.onClickActivitas()
    }

    fun onClickCalendar(view: View) {
        homePenjagaListener.onClickJadwalPicket()
    }

    fun onClickDataNapi(view: View) {
        homePenjagaListener.onClickDataNapi()
    }
}