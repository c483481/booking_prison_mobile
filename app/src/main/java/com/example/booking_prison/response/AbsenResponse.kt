package com.example.booking_prison.response

data class AbsenResponse(
    val cell: String,
    val createdAt: Int,
    val modifiedBy: ModifyBy,
    val name: String,
    val napiXid: String,
    val tema: String,
    val updatedAt: Int,
    val version: Int,
    val xid: String
)