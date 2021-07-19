package com.yatoooon.hilt_example.data.api

import com.yatoooon.hilt_example.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}