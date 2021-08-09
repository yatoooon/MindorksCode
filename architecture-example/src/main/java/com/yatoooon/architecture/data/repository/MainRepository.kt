package com.yatoooon.architecture.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yatoooon.architecture.data.api.ApiService
import com.yatoooon.architecture.data.model.Item
import com.yatoooon.architecture.data.source.RepoPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 10

class MainRepository @Inject constructor(private val apiService: ApiService) {

    fun getPagingData(): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { RepoPagingSource(apiService) }
        ).flow
    }
}
