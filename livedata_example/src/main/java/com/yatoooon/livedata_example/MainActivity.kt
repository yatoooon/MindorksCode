package com.yatoooon.livedata_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val model: NameViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //最简单的livedata例子
        model.currentName.observe(this, Observer<String> { newName ->
            tv_name.text = newName
        })
        bt_change.setOnClickListener {
            model.currentName.value = "anotherName"
        }

        //（1）SingleLiveEvent只观察一次事件 ，SingleLiveEvent仅限于一个观察者。如果添加了多个则只会调用一个，并且不能保证哪一个。
        //（2）数据倒灌是一种形象的说法，它是指先setValue/postValue，后调用observe(new Obs())，至此收到了回调。然后再调用observe(new anotherObs())，如果还能收到第一次的回调，也就是旧数据。解决方案可以参考开源项目：UnPeek-LiveData   可以使粘性事件变为非粘性事件
        model.msg.value = "msg!!!"
        model.msg.observe(this, Observer<String> { newMsg ->
            println("first observe msg:$newMsg")
        })
        model.msg.observe(this, Observer<String> { newMsg ->
            println("second observe msg:$newMsg")
        })

    }

}
