package com.example.booking_prison.network

import com.example.asisten_damkar.response.Response
import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.response.AbsenResponse
import com.example.booking_prison.utils.getBaseUrl
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface AbsenNetwork {
    @GET("/absen")
    fun getAbsenList(
        @Header("Authorization") token: String,
        @Query("limit") limit: Int = 10,
        @Query("showAll") showAll: Boolean = false,
        @Query("filters[tema]") tema: String? = null
    ): Call<Response<ResponseList<AbsenResponse>>>
    companion object {
        operator fun invoke(): AbsenNetwork {
            return Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AbsenNetwork::class.java)
        }
    }
}