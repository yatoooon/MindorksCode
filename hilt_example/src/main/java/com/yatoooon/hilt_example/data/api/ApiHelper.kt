package com.yatoooon.hilt_example.data.api

import com.yatoooon.hilt_example.data.model.User
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers(): Response<List<User>>
}
