package com.example.booking_prison.view

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.R
import com.example.booking_prison.adapter.AbsenAdapter
import com.example.booking_prison.databinding.ActivityAbsenBinding
import com.example.booking_prison.listener.AbsenListener
import com.example.booking_prison.listener.OnClickAdapter
import com.example.booking_prison.response.AbsenResponse
import com.example.booking_prison.utils.LoginUtils
import com.example.booking_prison.utils.hide
import com.example.booking_prison.utils.show
import com.example.booking_prison.utils.toast
import com.example.booking_prison.view_model.AbsenViewModel

class AbsenActivity : AppCompatActivity(), AbsenListener {
    lateinit var binding: ActivityAbsenBinding
    lateinit var dialog: Dialog
    lateinit var name: TextView
    lateinit var id: TextView
    lateinit var cell: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_absen)
        setContentView(binding.root)

        val tema = intent.getStringExtra("tema")!!
        binding.titleText.text = tema

        dialog = Dialog(this)
        dialog.setContentView(R.layout.pop_up_absen_detail)
        dialog.findViewById<Button>(R.id.pop_up_button).setOnClickListener {
            dialog.dismiss()
        }
        name = dialog.findViewById(R.id.pop_up_name)
        id = dialog.findViewById(R.id.pop_up_id)
        cell = dialog.findViewById(R.id.pop_up_cell)


        val model = ViewModelProvider(this)[AbsenViewModel::class.java]
        model.loginUtils = LoginUtils(this)
        model.absenListener = this

        binding.model = model

        model.fetch(tema)
    }

    override fun onLoading() {
        binding.loading.show()
    }

    override fun onFailed() {
        binding.loading.hide()
        toast("Something wrong, please try again later")
    }

    override fun onSuccess(data: ResponseList<AbsenResponse>) {
        binding.loading.hide()
        if(data.items.count() == 0) {
            binding.emptyText.visibility = View.VISIBLE
            return
        }
        binding.scrollView.visibility = View.VISIBLE
        val listener = object : OnClickAdapter<AbsenResponse> {
            override fun onClick(data: AbsenResponse) {
                name.text = data.name
                cell.text = data.cell
                id.text = data.xid

                dialog.show()
            }
        }

        val adapter = AbsenAdapter(data.items, listener)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
    }

    override fun onClickBack() {
        startActivity(Intent(this, ActivitasActivity::class.java))
        finish()
    }

    override fun onClickAdd() {
        toast("pergi ke halaman add")
    }
}