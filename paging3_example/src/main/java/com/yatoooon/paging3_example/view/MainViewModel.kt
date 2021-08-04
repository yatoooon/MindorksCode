package com.yatoooon.paging3_example.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yatoooon.paging3_example.data.repository.Repository
import com.yatoooon.paging3_example.data.model.Item
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {
    val pagingdata: Flow<PagingData<Item>> = Repository.getPagingData().cachedIn(viewModelScope)
}
