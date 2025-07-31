package com.example.shift_testtask.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RandomUser(
    @SerialName("info")
    val info: Info = Info(),
    @SerialName("results")
    val results: List<Result> = listOf()
)