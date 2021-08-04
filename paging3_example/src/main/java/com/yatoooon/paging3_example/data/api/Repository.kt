package com.yatoooon.paging3_example.data.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yatoooon.paging3_example.data.model.Item
import com.yatoooon.paging3_example.data.source.RepoPagingSource
import kotlinx.coroutines.flow.Flow

object Repository {

    private const val PAGE_SIZE = 50

    private val gitHubService = GitHubService.create()

    fun getPagingData(): Flow<PagingData<Item>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { RepoPagingSource(gitHubService) }
        ).flow
    }

}
