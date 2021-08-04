package com.yatoooon.paging3_example.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yatoooon.paging3_example.data.api.GitHubService
import com.yatoooon.paging3_example.data.model.Item

class RepoPagingSource(private val gitHubService: GitHubService) : PagingSource<Int, Item>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val page = params.key ?: 1 // set page 1 as default
            val pageSize = params.loadSize
            val repoResponse = gitHubService.searchRepos(page, pageSize)
            val repoItems = repoResponse.items
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (repoItems.isNotEmpty()) page + 1 else null
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? = null

}
