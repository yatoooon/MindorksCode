package com.common.inject_example.dagger2

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.common.inject_example.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named


class Dagger2Activity : AppCompatActivity() {
    @Inject  //@Inject  定义依赖项    Dagger2Activity就是依赖消费者
    @Named("Student")
    lateinit var student1: Student

    @Inject
    @Named("Student")
    lateinit var student2: Student

    @Inject
    @Named("StudentScope")
    lateinit var student3: Student

    @Inject
    @Named("StudentScope")
    lateinit var student4: Student

    @Inject
    lateinit var sp: SharedPreferences


    @Inject
    lateinit var advanceImpl: AdvanceImpl



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        DaggerActivityComponent.builder()
//            .activityModule(ActivityModule())
//            .appComponent(MyApplication.getAppComponent(this))
//            .build()
//            .inject(this)
        DaggerActivityComponent.factory()
            .create(MyApplication.getAppComponent(this))
            .inject(this)



        sp.run { edit().putString("name", "test").commit() }
        student1.name = "yatoooon"

        tv_name.text = student1.name



        tv_name.text = "sp：" + sp.getString("name", "def") +
                "无Scope：" + (student1 === student2).toString() + "有Scope：" + (student3 === student4).toString()


        advanceImpl.printName()
    }
}
