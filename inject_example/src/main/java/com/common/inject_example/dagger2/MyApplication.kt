package com.common.inject_example.dagger2

import android.app.Application
import android.content.Context

class MyApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
//            .appModule(AppModule(this))
            .application(this)
            .build()
        appComponent.inject(this)

    }

    companion object {
        fun getAppComponent(context: Context): AppComponent {
            val myApplication = context.applicationContext as MyApplication
            return myApplication.appComponent
        }
    }
}