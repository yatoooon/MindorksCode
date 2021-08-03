package com.yatoooon.koin_example.di

import com.yatoooon.koin_example.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }
}