package com.example.shift_testtask.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Id(
    @SerialName("name")
    val name: String = "",
    @SerialName("value")
    val value: String = ""
)