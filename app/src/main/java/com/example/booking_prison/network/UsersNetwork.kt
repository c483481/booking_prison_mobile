package com.example.booking_prison.network

import com.example.asisten_damkar.response.Response
import com.example.booking_prison.utils.getBaseUrl
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UsersNetwork {

    @POST("/users/password")
    fun changePassword(
        @Header("Authorization") token: String,
        @Body data: BodyChangePassword
    ): Call<Response<Any>>

    companion object {
        operator fun invoke(): UsersNetwork {
            return Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UsersNetwork::class.java)
        }
    }
}

data class BodyChangePassword(
    val password: String
)