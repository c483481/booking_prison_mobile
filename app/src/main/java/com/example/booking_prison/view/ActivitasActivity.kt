package com.example.booking_prison.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.booking_prison.R
import com.example.booking_prison.databinding.ActivityActivitasBinding
import com.example.booking_prison.listener.ActivitasListener
import com.example.booking_prison.utils.toast
import com.example.booking_prison.view_model.AktivitasViewModel

class ActivitasActivity : AppCompatActivity(), ActivitasListener {
    lateinit var binding: ActivityActivitasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_activitas)
        setContentView(binding.root)
        val model = ViewModelProvider(this)[AktivitasViewModel::class.java]
        model.activitasListener = this
        binding.model = model
    }

    override fun onClickList(theme: String) {
        val i = Intent(this, AbsenActivity::class.java)
        i.putExtra("tema", theme)
        startActivity(i)
        finish()
    }

    override fun onClickBackButton() {
        val i = Intent(this, HomePenjagaActivity::class.java)
        startActivity(i)
        finish()
    }
}