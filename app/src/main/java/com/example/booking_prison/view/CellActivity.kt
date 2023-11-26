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
        val i  = Intent(this, AddCellActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun onFetch() {
        binding.loading.show()
    }

    override fun onFailed() {
        binding.loading.hide()
    }

    override fun onSuccessFetch(data: ResponseList<CellResponse>) {
        binding.loading.hide()
        if(data.items.size == 0) {
            binding.emptyText.visibility = View.VISIBLE
            return
        }

        binding.scrollView.visibility = View.VISIBLE

        val onClickAdapter = object : OnClickAdapter<CellResponse> {
            override fun onClick(data: CellResponse) {
                val i = Intent(baseContext, NapiActivity::class.java)
                i.putExtra("cellName", data.name)
                i.putExtra("cellXid", data.xid)
                startActivity(i)
                finish()
            }
        }

        val adapter = CellAdapter(data.items, onClickAdapter)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }
}