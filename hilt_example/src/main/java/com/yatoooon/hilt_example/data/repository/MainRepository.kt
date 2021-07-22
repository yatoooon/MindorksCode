package com.yatoooon.hilt_example.data.repository

import com.yatoooon.hilt_example.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
}
