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
import com.example.booking_prison.adapter.NapiAdapter
import com.example.booking_prison.databinding.ActivityNapiBinding
import com.example.booking_prison.listener.NapiListener
import com.example.booking_prison.listener.OnClickAdapter
import com.example.booking_prison.response.CellResponse
import com.example.booking_prison.response.NapiResponse
import com.example.booking_prison.utils.hide
import com.example.booking_prison.utils.show
import com.example.booking_prison.utils.toast
import com.example.booking_prison.view_model.NapiViewModel

class NapiActivity : AppCompatActivity(), NapiListener {
    lateinit var binding: ActivityNapiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_napi)
        setContentView(binding.root)

        val model =  ViewModelProvider(this)[NapiViewModel::class.java]
        model.napiListener = this
        val cellName = intent.getStringExtra("cellName")!!
        model.cellName = cellName

        binding.model = model

        model.fetchNapi()
    }

    override fun onFetch() {
        binding.loading.show()
    }

    override fun onSuccessFetch(data: ResponseList<NapiResponse>) {
        binding.loading.hide()
        if(data.items.size == 0) {
            binding.emptyText.visibility = View.VISIBLE
            return
        }

        binding.scrollView.visibility = View.VISIBLE

        val onClickAdapter = object : OnClickAdapter<NapiResponse> {
            override fun onClick(data: NapiResponse) {
                toast(data.xid)
            }
        }

        val adapter = NapiAdapter(data.items, onClickAdapter)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    override fun onFailed() {
        binding.loading.hide()
        toast("something wrong, please try again later")
    }

    override fun onClickBack() {
        startActivity(Intent(this, CellActivity::class.java))
        finish()
    }

    override fun onClickAdd() {
        toast("add napi")
    }
}