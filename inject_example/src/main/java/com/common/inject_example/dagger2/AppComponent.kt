package com.common.inject_example.dagger2

import android.app.Application
import android.content.SharedPreferences
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])   //@Component用在接口上 生成相应的组件类 作为提供者和依赖者之前的桥梁 ，从module中获取依赖项 然后 向依赖者 提供所需要的依赖项
interface AppComponent {  //负责注入   把module提供的对象inject到activity中
    fun inject(application: MyApplication)

    val application: Application

    val sharedPreferences:SharedPreferences
}