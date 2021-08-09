package com.yatoooon.architecture.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yatoooon.architecture.data.model.Item
import com.yatoooon.architecture.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(mainRepository: MainRepository) : ViewModel() {
    val pagingData: Flow<PagingData<Item>> = mainRepository.getPagingData().cachedIn(viewModelScope)
}