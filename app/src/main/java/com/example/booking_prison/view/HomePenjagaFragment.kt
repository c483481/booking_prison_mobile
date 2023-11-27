package com.example.booking_prison.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.booking_prison.R
import com.example.booking_prison.databinding.FragmentHomePenjagaBinding
import com.example.booking_prison.listener.HomePenjagaListener
import com.example.booking_prison.view_model.HomePenjagaViewModel

class HomePenjagaFragment : Fragment(), HomePenjagaListener {
    lateinit var binding: FragmentHomePenjagaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePenjagaBinding.inflate(inflater)

        val model = ViewModelProvider(this)[HomePenjagaViewModel::class.java]

        model.homePenjagaListener = this

        binding.model = model

        return binding.root
    }

    override fun onClickAntrtian() {
        val i = Intent(context, AntrianActivity::class.java)
        startActivity(i)
    }

    override fun onClickActivitas() {
        val i = Intent(context, ActivitasActivity::class.java)
        startActivity(i)
    }

    override fun onClickJadwalPicket() {
        Toast.makeText(context, "jadwal", Toast.LENGTH_LONG).show()
    }

    override fun onClickDataNapi() {
        val i = Intent(context, CellActivity::class.java)
        startActivity(i)
    }
}