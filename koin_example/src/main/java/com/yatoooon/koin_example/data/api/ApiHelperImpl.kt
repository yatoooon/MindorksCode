package com.yatoooon.koin_example.data.api

import com.yatoooon.koin_example.data.model.User
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(): Response<List<User>> = apiService.getUsers()

}