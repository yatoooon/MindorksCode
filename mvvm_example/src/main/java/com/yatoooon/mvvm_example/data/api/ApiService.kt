package com.yatoooon.mvvm_example.data.api

import com.yatoooon.mvvm_example.data.model.User
import io.reactivex.Single

interface ApiService {
    fun getUsers(): Single<List<User>>
}