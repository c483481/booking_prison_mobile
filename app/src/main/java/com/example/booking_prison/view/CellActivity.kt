package com.example.booking_prison.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.R
import com.example.booking_prison.adapter.CellAdapter
import com.example.booking_prison.databinding.ActivityCellBinding
import com.example.booking_prison.listener.CellListener
import com.example.booking_prison.listener.OnClickAdapter
import com.example.booking_prison.response.CellResponse
import com.example.booking_prison.utils.LoginUtils
import com.example.booking_prison.utils.hide
import com.example.booking_prison.utils.show
import com.example.booking_prison.utils.toast
import com.example.booking_prison.view_model.CellViewModel

class CellActivity : AppCompatActivity(), CellListener {
    lateinit var binding: ActivityCellBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cell)
        setContentView(binding.root)

        val model = ViewModelProvider(this)[CellViewModel::class.java]
        val loginUtils = LoginUtils(this)
        model.cellListener = this
        model.loginUtils = loginUtils

        binding.model = model

        model.fetchCell()
    }

    override fun onClickBack() {
        val i = Intent(this, HomePenjagaActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun onClickAdd() {
        toast("pergi ke halkaman tambah cell")
    }

    override fun onFetch() {
        binding.loading.show()
    }

    override fun onFailed() {
        binding.loading.hide()
    }

    override fun onSuccessFetch(data: ResponseList<CellResponse>) {
        binding.loading.hide()
        val onClickAdapter = object : OnClickAdapter<CellResponse> {
            override fun onClick(data: CellResponse) {
                toast(data.xid)
            }
        }

        val adapter = CellAdapter(data.items, onClickAdapter)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }
}