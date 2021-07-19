package com.yatoooon.hilt_example.data.api

import com.yatoooon.hilt_example.data.api.ApiHelper
import com.yatoooon.hilt_example.data.api.ApiService
import com.yatoooon.hilt_example.data.model.User
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): Response<List<User>> = apiService.getUsers()

}