package com.example.booking_prison.network

import com.example.asisten_damkar.response.Response
import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.response.BookingResponse
import com.example.booking_prison.utils.getBaseUrl
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface BookingNetwork {
    @POST("/booking")
    fun postCreateBooking(
        @Header("Authorization") token: String,
        @Body data: BodyBooking
    ): Call<Response<Any>>

    @GET("/booking")
    fun getListBooking(
        @Header("Authorization") token: String,
        @Query("filters[users]") isUsers: Boolean? = null,
        @Query("filters[notClear]") noClear: Boolean? = null,
        @Query("filters[today]") today: Boolean? = null,
        @Query("limit") limit: Int = 10,
        @Query("showAll") showAll: Boolean = false,
    ): Call<Response<ResponseList<BookingResponse>>>

    @PATCH("/booking/{xid}")
    fun updateStatus(
        @Header("Authorization") token: String,
        @Path("xid") xid: String
    ): Call<Response<Any>>

    @PUT("/booking/today/all")
    fun updateTodayStatus(@Header("Authorization") token: String): Call<Any>

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