package com.example.booking_prison.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asisten_damkar.response.Response
import com.example.booking_prison.network.BodyChangePassword
import com.example.booking_prison.network.UsersNetwork
import retrofit2.Call
import retrofit2.Callback
class UsersRepository {
    private val usersNetwork = UsersNetwork()

    fun changeLogin(token: String, payload: BodyChangePassword): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        usersNetwork.changePassword(token, payload).enqueue(object : Callback<Response<Any>> {
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