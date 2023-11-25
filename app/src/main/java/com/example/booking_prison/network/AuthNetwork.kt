package com.example.booking_prison.network

import com.example.asisten_damkar.response.Response
import com.example.booking_prison.response.LoginResponse
import com.example.booking_prison.utils.getBaseUrl
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthNetwork {
    @POST("/auth/login")
    fun login(
        @Body data: BodyLogin
    ): Call<Response<LoginResponse>>

    @POST("/auth/users")
    fun registerUser(
        @Body data: BodyRegister
    ): Call<Response<Any>>

    companion object {
        operator fun invoke(): AuthNetwork {
            return Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AuthNetwork::class.java)
        }
    }
}

data class BodyLogin(
    val username: String,
    val password: String
)

data class BodyRegister(
    val username: String,
    val password: String
)