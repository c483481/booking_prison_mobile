package com.example.booking_prison.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asisten_damkar.response.Response
import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.network.BodyNapi
import com.example.booking_prison.network.NapiNetwork
import com.example.booking_prison.response.NapiResponse
import retrofit2.Call
import retrofit2.Callback

class NapiRepository {
    private val napiNetwork = NapiNetwork()

    fun getListNapi(token: String, showAll: Boolean= false, limit: Int = 10, cellName: String? = null): LiveData<ResponseList<NapiResponse>?> {
        val data = MutableLiveData<ResponseList<NapiResponse>?>()

        napiNetwork.getListNapi(token, showAll = showAll, limit = limit, cellName = cellName).enqueue(object : Callback<Response<ResponseList<NapiResponse>>> {
            override fun onResponse(
                call: Call<Response<ResponseList<NapiResponse>>>,
                response: retrofit2.Response<Response<ResponseList<NapiResponse>>>
            ) {
                if(response.isSuccessful) {
                    data.value = response.body()!!.data
                    return
                }
                data.value = null
            }

            override fun onFailure(call: Call<Response<ResponseList<NapiResponse>>>, t: Throwable) {
                data.value = null
            }
        })


        return data
    }

    fun postAddNapi(token: String, payload: BodyNapi): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()

        napiNetwork.postAddNapi(token, payload).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: retrofit2.Response<Any>) {
                data.value = response.isSuccessful
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                data.value = false
            }
        })


        return data
    }
}