package com.example.booking_prison.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.booking_prison.R
import com.example.booking_prison.databinding.ActivityLoginBinding
import com.example.booking_prison.listener.LoginListener
import com.example.booking_prison.response.LoginResponse
import com.example.booking_prison.utils.hide
import com.example.booking_prison.utils.show
import com.example.booking_prison.utils.toast
import com.example.booking_prison.view_model.LoginViewModel

class LoginActivity : AppCompatActivity(), LoginListener {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setContentView(binding.root)
        val model = ViewModelProvider(this)[LoginViewModel::class.java]
        model.loginListener = this
        binding.model = model
    }

    override fun onStarted() {
        binding.loading.show()
    }

    override fun onNotValid() {
        binding.loading.hide()
        this.show()
    }

    override fun fallback(data: LiveData<LoginResponse?>) {
        data.observe(this, Observer {
            binding.loading.hide()
            if(it == null) {
                this.show()
                return@Observer
            }
            val i  = Intent(this, HomeActivity::class.java)
            startActivity(i)
            finish()
        })
    }

    override fun onSingIn() {
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun show() {
        toast("Username atau password salah")
    }
}