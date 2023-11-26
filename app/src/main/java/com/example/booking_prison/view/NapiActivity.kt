package com.example.booking_prison.view

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.R
import com.example.booking_prison.adapter.NapiAdapter
import com.example.booking_prison.databinding.ActivityNapiBinding
import com.example.booking_prison.listener.NapiListener
import com.example.booking_prison.listener.OnClickAdapter
import com.example.booking_prison.response.NapiResponse
import com.example.booking_prison.utils.LoginUtils
import com.example.booking_prison.utils.epochToDateString
import com.example.booking_prison.utils.hide
import com.example.booking_prison.utils.show
import com.example.booking_prison.utils.toast
import com.example.booking_prison.view_model.NapiViewModel

class NapiActivity : AppCompatActivity(), NapiListener {
    lateinit var binding: ActivityNapiBinding
    private lateinit var onClickAdapter: OnClickAdapter<NapiResponse>
    lateinit var addDialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_napi)
        setContentView(binding.root)

        val model =  ViewModelProvider(this)[NapiViewModel::class.java]
        model.napiListener = this
        val cellName = intent.getStringExtra("cellName")!!
        model.cellName = cellName
        model.loginUtils = LoginUtils(this)
        val cellXid = intent.getStringExtra("cellXid")!!

        addDialog = Dialog(this)
        addDialog.setContentView(R.layout.pop_up_add_napi)
        addDialog.findViewById<Button>(R.id.pop_up_add_button).setOnClickListener {
            val name = addDialog.findViewById<EditText>(R.id.pop_up_name).text.toString()
            val tanggal = addDialog.findViewById<EditText>(R.id.pop_up_long_time).text.toString()
            val reason = addDialog.findViewById<EditText>(R.id.pop_up_reason).text.toString()

            model.addNapi(name, reason, tanggal, cellXid)
        }

        addDialog.findViewById<Button>(R.id.pop_up_cancel_button).setOnClickListener {
            addDialog.dismiss()
        }

        val infoDialog = Dialog(this)
        infoDialog.setContentView(R.layout.pop_up_detail_profile)
        infoDialog.findViewById<Button>(R.id.pop_up_button).setOnClickListener {
            infoDialog.dismiss()
        }

        val name = infoDialog.findViewById<TextView>(R.id.pop_up_name)
        val tanggal = infoDialog.findViewById<TextView>(R.id.pop_up_long_time)
        val reason = infoDialog.findViewById<TextView>(R.id.pop_up_reason)
        val id = infoDialog.findViewById<TextView>(R.id.pop_up_id)
        val create = infoDialog.findViewById<TextView>(R.id.pop_up_create)
        val cell = infoDialog.findViewById<TextView>(R.id.pop_up_cell)

        onClickAdapter = object : OnClickAdapter<NapiResponse> {
            override fun onClick(data: NapiResponse) {
                name.text = "Nama: ${data.name}"
                tanggal.text = "Bebas Pada : ${epochToDateString(data.dateOut)}"
                reason.text = "Alasan: ${data.reason}"
                id.text = "No Id: ${data.xid}"
                create.text = "tanggal Masuk: ${epochToDateString(data.createdAt)}"
                cell.text = "Cell: ${data.cell}"

                infoDialog.show()
            }
        }

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
        addDialog.show()
    }

    override fun onNotValid() {
        binding.loading.hide()
        toast("please fill the form correctly")
    }

    override fun onSuccessAdd() {
        startActivity(Intent(this, CellActivity::class.java))
        finish()
    }
}