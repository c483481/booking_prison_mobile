package com.example.booking_prison.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.booking_prison.R
import com.example.booking_prison.listener.OnClickAdapter
import com.example.booking_prison.response.NapiResponse

class NapiAdapter(private val items: Array<NapiResponse>, private val listener: OnClickAdapter<NapiResponse>): RecyclerView.Adapter<NapiAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.adapter_napi_name)
        val container: LinearLayout = itemView.findViewById(R.id.adapter_napi_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.adapter_napi, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.name.text = item.name

        holder.container.setOnClickListener {
            listener.onClick(item)
        }
    }
}