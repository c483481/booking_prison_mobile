package com.example.booking_prison.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booking_prison.R
import com.example.booking_prison.databinding.ActivityRegisterBinding
import com.example.booking_prison.listener.RegisterListener
import com.example.booking_prison.utils.hide
import com.example.booking_prison.utils.show
import com.example.booking_prison.utils.toast
import com.example.booking_prison.view_model.RegisterViewModel

class RegisterActivity : AppCompatActivity(), RegisterListener {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        setContentView(binding.root)
        val model = ViewModelProvider(this)[RegisterViewModel::class.java]
        model.registerListener = this
        binding.model = model
    }

    override fun onStarted() {
        binding.loading.show()
    }

    override fun onNotValid() {
        binding.loading.hide()
        toast("isi form dengan benar")
    }

    override fun fallback(data: LiveData<Boolean>) {
        data.observe(this, Observer {
            if(it) {
                toast("Berhasil Register")
                val i = Intent(this, LoginActivity::class.java)
                startActivity(i)
                finish()
                return@Observer
            }
            this.show()
        })
    }

    override fun onSingUp() {
        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
        finish()
    }
    private fun show() {
        toast("Username sudah digunakan, mohon gantti username")
    }
}