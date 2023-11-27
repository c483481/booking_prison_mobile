package com.example.booking_prison.view

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.R
import com.example.booking_prison.adapter.NapiAdapter
import com.example.booking_prison.databinding.ActivityAddAbsenBinding
import com.example.booking_prison.listener.AddAbsenListener
import com.example.booking_prison.listener.OnClickAdapter
import com.example.booking_prison.response.NapiResponse
import com.example.booking_prison.utils.LoginUtils
import com.example.booking_prison.utils.hide
import com.example.booking_prison.utils.show
import com.example.booking_prison.utils.toast
import com.example.booking_prison.view_model.AddAbsenViewModel

class AddAbsenActivity : AppCompatActivity(), AddAbsenListener {
    lateinit var binding: ActivityAddAbsenBinding
    private lateinit var tema: String
    private lateinit var dialog: Dialog
    private lateinit var sure: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_absen)
        setContentView(binding.root)
        tema = intent.getStringExtra("tema")!!
        binding.titleText.text = tema

        val model = ViewModelProvider(this)[AddAbsenViewModel::class.java]
        model.loginUtils = LoginUtils(this)
        model.addAbsenListener = this

        binding.model = model

        dialog = Dialog(this)
        dialog.setContentView(R.layout.pop_up_sure_absen)
        sure = dialog.findViewById(R.id.pop_up_sure_button)
        dialog.findViewById<Button>(R.id.pop_up_cancel_button)

        model.fetchNapi()
    }

    override fun onLoading() {
        binding.loading.show()
    }

    override fun clearLoading() {
        binding.loading.hide()
    }

    override fun onFailed() {
        toast("something wrong, please try again later")
    }

    override fun onSuccess(data: ResponseList<NapiResponse>) {
        if(data.items.isEmpty()) {
            binding.emptyText.visibility = View.VISIBLE
            return
        }
        binding.scrollView.visibility = View.VISIBLE
        val listener = object: OnClickAdapter<NapiResponse>{
            override fun onClick(data: NapiResponse) {
                sure.setOnClickListener {
                    binding.model!!.addAbsen(data.xid, tema)
                }
                dialog.show()
            }
        }

        val adapter = NapiAdapter(data.items, listener)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
    }

    override fun onClickSuccessAdd() {
        toast("berhasil menambahkan absen")
        onClickBack()
    }

    override fun onClickBack() {
        val i = Intent(this, AbsenActivity::class.java)
        i.putExtra("tema", tema)
        startActivity(i)
        finish()
    }
}