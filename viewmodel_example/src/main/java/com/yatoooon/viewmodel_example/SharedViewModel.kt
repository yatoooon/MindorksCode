package com.yatoooon.viewmodel_example

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val message = MutableLiveData<String>()
}