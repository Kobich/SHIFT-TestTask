package com.example.shift_testtask.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Name(
    @SerialName("first")
    val first: String = "",
    @SerialName("last")
    val last: String = "",
    @SerialName("title")
    val title: String = ""
)