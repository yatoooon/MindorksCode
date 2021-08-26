package com.common.inject_example.dagger2

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {  //@Module类 依赖提供者  提供可以注入的对象

    @Provides
    @Singleton //@Singleton 确保全局类的单个实例
    fun provideSharedPreferences(): SharedPreferences = application.getSharedPreferences(
        "spfile",
        Context.MODE_PRIVATE
    )

    @Provides
    fun provideApplication(): Application {
        return application
    }
}