package com.example.booking_prison.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.booking_prison.R
import com.example.booking_prison.databinding.ActivityAddCellBinding
import com.example.booking_prison.listener.AddCellListener
import com.example.booking_prison.utils.LoginUtils
import com.example.booking_prison.utils.hide
import com.example.booking_prison.utils.show
import com.example.booking_prison.utils.toast
import com.example.booking_prison.view_model.AddCellViewModel

class AddCellActivity : AppCompatActivity(), AddCellListener {
    lateinit var binding: ActivityAddCellBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_cell)
        setContentView(binding.root)
        val model = ViewModelProvider(this)[AddCellViewModel::class.java]
        model.loginUtils = LoginUtils(this)
        model.addCellListener = this
        binding.model = model
    }

    override fun onLoading() {
        binding.loading.show()
    }

    override fun onFailed() {
        binding.loading.hide()
        toast("something wrong, please try again later")
    }

    override fun onSuccess() {
        binding.loading.hide()
        toast("success add cell")
        val i = Intent(this, CellActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun onClickBack() {
        val i = Intent(this, CellActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun onNotValid() {
        binding.loading.hide()
        toast("please fill the form correctly")
    }
}