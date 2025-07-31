package com.example.shift_testtask.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Registered(
    @SerialName("age")
    val age: Int = 0,
    @SerialName("date")
    val date: String = ""
)