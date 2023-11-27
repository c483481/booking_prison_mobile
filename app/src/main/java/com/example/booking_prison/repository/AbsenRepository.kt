package com.example.booking_prison.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asisten_damkar.response.Response
import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.network.AbsenNetwork
import com.example.booking_prison.response.AbsenResponse
import retrofit2.Call
import retrofit2.Callback

class AbsenRepository {
    private val absenNetwork = AbsenNetwork()

    fun getListAbsen(token: String, limit: Int = 10, showAll: Boolean = false, tema: String? = null): LiveData<ResponseList<AbsenResponse>?> {
        val data = MutableLiveData<ResponseList<AbsenResponse>?>()

        absenNetwork.getAbsenList(token, limit = limit, showAll = showAll, tema = tema).enqueue(object: Callback<Response<ResponseList<AbsenResponse>>> {
            override fun onResponse(
                call: Call<Response<ResponseList<AbsenResponse>>>,
                response: retrofit2.Response<Response<ResponseList<AbsenResponse>>>
            ) {
                if(response.isSuccessful) {
                    data.value = response.body()!!.data
                    return
                }
                data.value = null
            }

            override fun onFailure(
                call: Call<Response<ResponseList<AbsenResponse>>>,
                t: Throwable
            ) {
                data.value = null
            }

        })

        return data
    }
}