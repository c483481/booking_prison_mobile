package com.example.booking_prison.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.R
import com.example.booking_prison.adapter.BookingPenjagaAdapter
import com.example.booking_prison.databinding.ActivityAntrianBinding
import com.example.booking_prison.listener.AntrianListener
import com.example.booking_prison.response.BookingResponse
import com.example.booking_prison.utils.LoginUtils
import com.example.booking_prison.utils.hide
import com.example.booking_prison.utils.show
import com.example.booking_prison.utils.toast
import com.example.booking_prison.view_model.AntrianViewModel

class AntrianActivity : AppCompatActivity(), AntrianListener {
    lateinit var binding: ActivityAntrianBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_antrian)
        setContentView(binding.root)

        val model = ViewModelProvider(this)[AntrianViewModel::class.java]
        model.antrianListener = this
        model.loginUtils = LoginUtils(this)
        binding.model = model

        model.fetchData()
    }

    override fun onFetchAntrian() {
        binding.loading.show()
    }

    override fun onGetAntrianData(data: ResponseList<BookingResponse>?) {
        binding.loading.hide()
        if(data == null) {
            toast("Something wrong, please try again later")
            return
        }
        if(data.count == 0) {
            binding.emptyText.visibility = View.VISIBLE
            return
        }

        binding.scrollView.visibility = View.VISIBLE
        val adapter = BookingPenjagaAdapter(data.items)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    override fun onClickBack() {
        val i = Intent(this, HomePenjagaActivity::class.java)
        startActivity(i)
        finish()
    }
}