package com.common.inject_example.dagger2

import android.app.Application
import android.content.SharedPreferences
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])   //@Component用在接口上 生成相应的组件类 作为提供者和依赖者之前的桥梁 ，从module中获取依赖项 然后 向依赖者 提供所需要的依赖项
interface AppComponent {  //负责注入   把module提供的对象inject到activity中
    fun inject(application: MyApplication)

    val application: Application

    val sharedPreferences:SharedPreferences

    @Component.Builder //使用 @Component.Builder 和 @BindsInstance 来自定义 Builder 类,那么被@BindsInstance注解方法里面的参数在 Builder 类中都有对应的成员变量。
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }
}