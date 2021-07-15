package com.yatoooon.mvvm_example.data.repository


import com.yatoooon.mvvm_example.data.api.ApiHelper
import com.yatoooon.mvvm_example.data.model.User
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getUsers(): Single<List<User>> {
        return apiHelper.getUsers()
    }

}