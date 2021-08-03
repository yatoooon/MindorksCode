package com.yatoooon.koin_example.di

import com.yatoooon.koin_example.ui.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get(),get())
    }
}