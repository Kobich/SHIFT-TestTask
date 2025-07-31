package com.example.shift_testtask.data.api

import com.example.shift_testtask.data.model.RandomUser
import retrofit2.Call
import retrofit2.http.GET

interface RandomUserApi {
    @GET("api/?nat=us")
    suspend fun getRandomUsers(
        @retrofit2.http.Query("page") page: Int,
        @retrofit2.http.Query("results") results: Int
    ): RandomUser

    //TODO: Other endpoints
}