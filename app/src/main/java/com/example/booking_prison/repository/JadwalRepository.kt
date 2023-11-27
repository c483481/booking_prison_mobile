package com.example.booking_prison.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asisten_damkar.response.Response
import com.example.booking_prison.network.BodyJadwal
import com.example.booking_prison.network.JadwalNetwork
import com.example.booking_prison.response.JadwalResponse
import retrofit2.Call
import retrofit2.Callback

class JadwalRepository {
    private val jadwalNetwork = JadwalNetwork()

    fun getJadwal(token: String): LiveData<JadwalResponse?> {
        val data = MutableLiveData<JadwalResponse?>()

        jadwalNetwork.getJadwal(token).enqueue(object : Callback<Response<JadwalResponse>> {
            override fun onResponse(
                call: Call<Response<JadwalResponse>>,
                response: retrofit2.Response<Response<JadwalResponse>>
            ) {
                if(response.isSuccessful) {
                    data.value = response.body()!!.data
                    return
                }
                data.value = null
            }

            override fun onFailure(call: Call<Response<JadwalResponse>>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

    fun changeJadwal(token: String, payload: BodyJadwal): LiveData<JadwalResponse?> {
        val data = MutableLiveData<JadwalResponse?>()

        jadwalNetwork.postJadwal(token, payload).enqueue(object : Callback<Response<JadwalResponse>> {
            override fun onResponse(
                call: Call<Response<JadwalResponse>>,
                response: retrofit2.Response<Response<JadwalResponse>>
            ) {
                if(response.isSuccessful) {
                    data.value = response.body()!!.data
                    return
                }
                data.value = null
            }

            override fun onFailure(call: Call<Response<JadwalResponse>>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }
}