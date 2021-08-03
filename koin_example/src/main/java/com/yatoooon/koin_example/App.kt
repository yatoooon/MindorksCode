package com.yatoooon.koin_example

import android.app.Application
import com.yatoooon.koin_example.di.appModule
import com.yatoooon.koin_example.di.repoModule
import com.yatoooon.koin_example.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}