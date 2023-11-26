package com.example.booking_prison.response

data class BookingResponse(
    val alamat: String,
    val barang: String,
    val clear: Boolean,
    val createdAt: Int,
    val date: Int,
    val modifiedBy: ModifyBy,
    val name: String,
    val noKtp: String,
    val noTelp: String,
    val sesi: String,
    val updatedAt: Int,
    val version: Int,
    val xid: String
)