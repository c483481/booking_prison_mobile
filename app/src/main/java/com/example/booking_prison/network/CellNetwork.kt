package com.example.booking_prison.network

import com.example.asisten_damkar.response.Response
import com.example.asisten_damkar.response.ResponseList
import com.example.booking_prison.response.CellResponse
import com.example.booking_prison.utils.getBaseUrl
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface CellNetwork {
    @GET("/cell")
    fun getListCell(
        @Header("Authorization") token: String,
        @Query("limit") limit: Int = 10,
        @Query("showAll") showAll: Boolean = false,
    ): Call<Response<ResponseList<CellResponse>>>

    @POST("/cell")
    fun postAddCell(
        @Header("Authorization") token: String,
        @Body data: BodyCell
    ): Call<Any>

    companion object {
        operator fun invoke(): CellNetwork {
            return Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CellNetwork::class.java)
        }
    }
}

data class BodyCell(
    val name: String,
    val max: Int
)