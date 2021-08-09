package com.yatoooon.architecture.data.api

import com.yatoooon.architecture.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/repositories?sort=stars&q=Android")
    suspend fun searchRepos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): SearchResponse

}
