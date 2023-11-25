package com.example.booking_prison.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun dateToIsoString(date: Date): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    return sdf.format(date)
}