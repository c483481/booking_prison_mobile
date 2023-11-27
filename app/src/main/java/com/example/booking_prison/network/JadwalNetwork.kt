package com.example.booking_prison.network

import com.example.asisten_damkar.response.Response
import com.example.booking_prison.response.JadwalResponse
import com.example.booking_prison.utils.getBaseUrl
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface JadwalNetwork {
    @GET("/jadwal")
    fun getJadwal(
        @Header("Authorization") token: String
    ): Call<Response<JadwalResponse>>
    @POST("/jadwal")
    fun postJadwal(
        @Header("Authorization") token: String,
        @Body data: BodyJadwal
    ): Call<Response<JadwalResponse>>
    companion object {
        operator fun invoke(): JadwalNetwork {
            return Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(JadwalNetwork::class.java)
        }
    }
}

data class BodyJadwal(
    val hari: String,
    val name: String
)
