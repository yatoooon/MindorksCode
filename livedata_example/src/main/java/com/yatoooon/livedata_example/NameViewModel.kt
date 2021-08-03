package com.yatoooon.livedata_example

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NameViewModel : ViewModel() {

    val currentName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val msg: SingleLiveEvent<String> by lazy {
        SingleLiveEvent<String>()
    }
}