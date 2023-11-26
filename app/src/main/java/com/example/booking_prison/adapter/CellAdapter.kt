package com.example.booking_prison.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.booking_prison.R
import com.example.booking_prison.listener.OnClickAdapter
import com.example.booking_prison.response.CellResponse

class CellAdapter(private val items: Array<CellResponse>, private val listener: OnClickAdapter<CellResponse>): RecyclerView.Adapter<CellAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.adapter_cell_name)
        val count: TextView = itemView.findViewById(R.id.adapter_cell_count)
        val container: LinearLayout = itemView.findViewById(R.id.adapter_cell_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_cell, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.name.text = item.name
        holder.count.text = "${item.count}/${item.max}"
        holder.container.setOnClickListener {
            listener.onClick(item)
        }
    }
}