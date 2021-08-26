package com.common.inject_example.dagger2

import javax.inject.Scope

@Scope //声明这是一个自定义@Scope注解 用于指定依赖对象存在的范围
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope
