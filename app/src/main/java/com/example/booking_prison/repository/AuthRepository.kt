package com.example.booking_prison.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asisten_damkar.response.Response
import com.example.booking_prison.network.AuthNetwork
import com.example.booking_prison.network.BodyLogin
import com.example.booking_prison.network.BodyRegister
import com.example.booking_prison.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback

class AuthRepository {
    private val authNetwork = AuthNetwork()

    fun login(payload: BodyLogin): LiveData<LoginResponse?> {
        val data = MutableLiveData<LoginResponse?>()

        authNetwork.login(payload).enqueue(object : Callback<Response<LoginResponse>> {
            override fun onResponse(
                call: Call<Response<LoginResponse>>,
                response: retrofit2.Response<Response<LoginResponse>>
            ) {
                if(response.isSuccessful) {
                    data.value = response.body()!!.data
                    return
                }
                data.value = null
            }

            override fun onFailure(call: Call<Response<LoginResponse>>, t: Throwable) {
                data.value = null
            }
        })

        return data
    }

    fun registerUser(payload: BodyRegister): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()

        authNetwork.registerUser(payload).enqueue(object : Callback<Response<Any>> {
            override fun onResponse(
                call: Call<Response<Any>>,
                response: retrofit2.Response<Response<Any>>
            ) {
                data.value = response.isSuccessful
            }

            override fun onFailure(call: Call<Response<Any>>, t: Throwable) {
                data.value = false
            }
        })

        return data
    }
}