package com.yatoooon.koin_example.ui.main.viewmodel

import androidx.lifecycle.*
import com.yatoooon.koin_example.data.model.User
import com.yatoooon.koin_example.data.repository.MainRepository
import com.yatoooon.koin_example.utils.NetworkHelper
import com.yatoooon.koin_example.utils.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>>
        get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                try {   //网络异常处理
                    mainRepository.getUsers().let {
                        if (it.isSuccessful) {
                            _users.postValue(Resource.success(it.body()))
                        } else _users.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }finally {

                }
            } else _users.postValue(Resource.error("No internet connection", null))
        }
    }
}