package com.example.booking_prison.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.booking_prison.R
import com.example.booking_prison.response.BookingResponse
import com.example.booking_prison.utils.epochToDateString

class BookingPenjagaAdapter(private val items: Array<BookingResponse>): RecyclerView.Adapter<BookingPenjagaAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.card_booking_name)
        val alamat: TextView = itemView.findViewById(R.id.card_booking_alamat)
        val antrian: TextView = itemView.findViewById(R.id.card_booking_antrian)
        val ktp: TextView = itemView.findViewById(R.id.card_booking_ktp)
        val noTelp: TextView = itemView.findViewById(R.id.card_booking_telp)
        val barang: TextView = itemView.findViewById(R.id.card_booking_barang)
        val sesi: TextView = itemView.findViewById(R.id.card_booking_sesi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_card_booking_penjaga, parent, false)
        return BookingPenjagaAdapter.ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.antrian.text = position.toString()
        holder.ktp.text = item.noKtp
        holder.alamat.text = item.alamat
        holder.name.text = item.name
        holder.barang.text = item.barang
        holder.noTelp.text = item.noTelp
        holder.sesi.text = item.sesi
    }
}