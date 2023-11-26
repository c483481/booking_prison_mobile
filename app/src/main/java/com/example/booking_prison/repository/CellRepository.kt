package com.example.booking_prison.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asisten_damkar.response.Response
import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.network.CellNetwork
import com.example.booking_prison.response.CellResponse
import retrofit2.Call
import retrofit2.Callback

class CellRepository {
    private val cellNetwork = CellNetwork()

    fun getListCell(token: String, limit: Int = 10, showAll: Boolean = false): LiveData<ResponseList<CellResponse>?> {
        val data = MutableLiveData<ResponseList<CellResponse>?>()

        cellNetwork.getListCell(token, limit, showAll).enqueue(object : Callback<Response<ResponseList<CellResponse>>> {
            override fun onResponse(
                call: Call<Response<ResponseList<CellResponse>>>,
                response: retrofit2.Response<Response<ResponseList<CellResponse>>>
            ) {
                if(response.isSuccessful) {
                    data.value = response.body()!!.data
                    return
                }
                data.value = null
            }

            override fun onFailure(call: Call<Response<ResponseList<CellResponse>>>, t: Throwable) {
                data.value = null
            }

        })

        return data
    }
}