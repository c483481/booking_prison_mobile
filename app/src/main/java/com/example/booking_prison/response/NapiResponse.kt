package com.example.booking_prison.response

data class NapiResponse(
    val cell: String,
    val createdAt: Int,
    val dateOut: Int,
    val modifiedBy: ModifyBy,
    val name: String,
    val reason: String,
    val updatedAt: Int,
    val version: Int,
    val xid: String
)