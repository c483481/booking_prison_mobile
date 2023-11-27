package com.example.booking_prison.view

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.booking_prison.R
import com.example.booking_prison.databinding.ActivityJadwalBinding
import com.example.booking_prison.listener.JadwalListener
import com.example.booking_prison.response.JadwalResponse
import com.example.booking_prison.utils.LoginUtils
import com.example.booking_prison.utils.hide
import com.example.booking_prison.utils.show
import com.example.booking_prison.utils.toast
import com.example.booking_prison.view_model.JadwalViewModel

class JadwalActivity : AppCompatActivity(), JadwalListener {
    lateinit var binding: ActivityJadwalBinding
    lateinit var hari: String
    lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_jadwal)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            onClickBackButton()
        }
        val model = ViewModelProvider(this)[JadwalViewModel::class.java]
        model.jadwalListener = this
        model.loginUtils = LoginUtils(this)
        dialog = Dialog(this)
        dialog.setContentView(R.layout.pop_up_change_jadwal)
        dialog.findViewById<Button>(R.id.pop_up_cancel_button).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<Button>(R.id.pop_up_change_button).setOnClickListener {
            val nama = dialog.findViewById<TextView>(R.id.pop_up_name).text.toString()
            model.changeJadwal(nama = nama, hari)
        }


        binding.minggu.setOnClickListener {
            hari = "minggu"
            dialog.show()
        }

        binding.sabtu.setOnClickListener {
            hari = "sabtu"
            dialog.show()
        }

        binding.jumat.setOnClickListener {
            hari = "jumat"
            dialog.show()
        }

        binding.kamis.setOnClickListener {
            hari = "kamis"
            dialog.show()
        }

        binding.rabu.setOnClickListener {
            hari = "rabu"
            dialog.show()
        }

        binding.selasa.setOnClickListener {
            hari = "selasa"
            dialog.show()
        }

        binding.senin.setOnClickListener {
            hari = "senin"
            dialog.show()
        }

        model.fetchJadwal()
    }

    override fun onLoading() {
        binding.loading.show()
    }

    override fun onClear() {
        binding.loading.hide()
    }

    override fun onSuccesChange() {
        toast("success change jadwal")
        dialog.dismiss()
    }

    override fun onFailed() {
        toast("something wrong, please try again later")
    }

    override fun onSuccess(data: JadwalResponse) {
        binding.jumatText.text = data.jumat
        binding.kamisText.text = data.kamis
        binding.rabuText.text = data.rabu
        binding.selasaText.text = data.selasa
        binding.seninText.text = data.senin
        binding.sabtuText.text = data.sabtu
        binding.mingguText.text = data.minggu
    }

    override fun onClickBackButton() {
        startActivity(Intent(this, JadwalActivity::class.java))
        finish()
    }

    override fun onNotValid() {
        toast("please fill the form correctly")
    }


}