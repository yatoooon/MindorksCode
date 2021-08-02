package com.yatoooon.mvvm_example.data.api

import com.yatoooon.mvvm_example.data.model.User

import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

}