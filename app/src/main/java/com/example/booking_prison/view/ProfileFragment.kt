package com.example.booking_prison.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.booking_prison.R
import com.example.booking_prison.databinding.FragmentProfileBinding
import com.example.booking_prison.listener.ProfileListener
import com.example.booking_prison.utils.LoginUtils
import com.example.booking_prison.utils.hide
import com.example.booking_prison.utils.show
import com.example.booking_prison.view_model.ProfileViewModel

class ProfileFragment : Fragment(), ProfileListener {
    lateinit var binding: FragmentProfileBinding
    lateinit var loginUtils: LoginUtils
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)

        loginUtils = LoginUtils(container!!.context)

        val model = ViewModelProvider(this)[ProfileViewModel::class.java]

        model.profileListener = this

        model.loginUtils = loginUtils

        binding.username.text = loginUtils.getUsername()

        binding.model = model

        return binding.root
    }

    override fun logOut() {
        loginUtils.logOut()
    }

    override fun started() {
        binding.loading.show()
    }

    override fun onNotValid() {
        binding.loading.hide()
        Toast.makeText(context, "Masukan password yang benar", Toast.LENGTH_LONG).show()
    }

    override fun onChangePassword(data: LiveData<Boolean>) {
        data.observe(this, Observer {
            binding.loading.hide()
            if(it) {
                Toast.makeText(context, "Success change password", Toast.LENGTH_LONG).show()
                return@Observer
            }
            Toast.makeText(context, "Failed change password, please try again later", Toast.LENGTH_LONG).show()
        })
    }
}