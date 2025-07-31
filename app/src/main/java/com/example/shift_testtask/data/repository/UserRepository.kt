package com.example.shift_testtask.data.repository

import com.example.shift_testtask.data.api.RandomUserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val api: RandomUserApi) {
    suspend fun getRandomUsers(count: Int = 20): List<com.example.shift_testtask.data.model.Result> = withContext(Dispatchers.IO) {
        val response = api.getRandomUsers(page = 1, results = count)
        response.results
    }
}