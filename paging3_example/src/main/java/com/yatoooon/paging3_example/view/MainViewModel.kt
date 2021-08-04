package com.yatoooon.paging3_example.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yatoooon.paging3_example.data.api.Repository
import com.yatoooon.paging3_example.data.model.Item
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {

    fun getPagingData(): Flow<PagingData<Item>> {
        return Repository.getPagingData().cachedIn(viewModelScope)
    }

}
