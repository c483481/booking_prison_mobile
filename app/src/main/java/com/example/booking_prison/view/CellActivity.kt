package com.example.booking_prison.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.booking_prison.R
import com.example.booking_prison.databinding.ActivityCellBinding
import com.example.booking_prison.utils.LoginUtils
import com.example.booking_prison.view_model.CellViewModel

class CellActivity : AppCompatActivity() {
    lateinit var binding: ActivityCellBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cell)
        setContentView(binding.root)

        val model = ViewModelProvider(this)[CellViewModel::class.java]
        val loginUtils = LoginUtils(this)


    }
}