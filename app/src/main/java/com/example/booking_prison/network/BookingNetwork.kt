package com.example.booking_prison.network

import com.example.asisten_damkar.response.Response
import com.example.booking_prison.utils.getBaseUrl
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface BookingNetwork {
    @POST("/booking")
    fun postCreateBooking(
        @Header("Authorization") token: String,
        @Body data: BodyBooking
    ): Call<Response<Any>>

    companion object {
        operator fun invoke(): BookingNetwork {
            return Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookingNetwork::class.java)
        }
    }
}

data class BodyBooking(
    val name: String,
    val noKtp: String,
    val noTelp: String,
    val sesi: String,
    val alamat: String,
    val barang: String,
    val date: String
)