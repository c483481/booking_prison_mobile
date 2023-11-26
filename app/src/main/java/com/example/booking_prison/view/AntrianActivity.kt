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
import com.example.booking_prison.listener.OnClickAdapter
import com.example.booking_prison.response.BookingResponse
import com.example.booking_prison.response.removeByXid
import com.example.booking_prison.utils.LoginUtils
import com.example.booking_prison.utils.hide
import com.example.booking_prison.utils.show
import com.example.booking_prison.utils.toast
import com.example.booking_prison.view_model.AntrianViewModel

class AntrianActivity : AppCompatActivity(), AntrianListener {
    lateinit var binding: ActivityAntrianBinding
    private var items: Array<BookingResponse> = arrayOf()
    lateinit var adapter: BookingPenjagaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_antrian)
        setContentView(binding.root)

        val model = ViewModelProvider(this)[AntrianViewModel::class.java]
        model.antrianListener = this
        model.loginUtils = LoginUtils(this)
        binding.model = model

        val listener: OnClickAdapter<BookingResponse> = object : OnClickAdapter<BookingResponse> {
            override fun onClick(data: BookingResponse) {
                model.updateStatusBooking(data.xid)
            }
        }

        adapter = BookingPenjagaAdapter(items, listener)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

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
        items = data.items
        adapter.updateData(items)
    }

    override fun onClickBack() {
        val i = Intent(this, HomePenjagaActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun onFailed() {
        binding.loading.hide()
        toast("Something wrong, please, try again later")
    }

    override fun onSuccessPatch(xid: String) {
        binding.loading.hide()
        items = items.removeByXid(xid)
        adapter.updateData(items)
        if(items.isEmpty()) {
            binding.scrollView.visibility = View.INVISIBLE
            binding.emptyText.visibility = View.VISIBLE
        }
    }
}