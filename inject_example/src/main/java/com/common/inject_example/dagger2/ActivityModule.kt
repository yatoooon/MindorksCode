package com.common.inject_example.dagger2

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ActivityModule() {  //@Module类 依赖提供者  提供可以注入的对象

    @Provides  //@Provides的方法，返回的对象可用于依赖注入
    @Named("Student") //@Named 区分两个返回值类型一样的时候  和@Qualifier一样 用于区分相同类型但具有不同实例的对象
    fun provideStudent() = Student()

    @Provides
    @Named("StudentScope")
    @ActivityScope  //自定义注解@ActivityScope  在这个活动域中，每次注入的对象都是一样的
    fun provideStudentWithScope() = Student()

}