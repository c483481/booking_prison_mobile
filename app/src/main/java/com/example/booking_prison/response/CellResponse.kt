package com.example.booking_prison.response

data class CellResponse(
    val count: Int,
    val createdAt: Int,
    val max: Int,
    val modifiedBy: ModifyBy,
    val name: String,
    val updatedAt: Int,
    val version: Int,
    val xid: String
)