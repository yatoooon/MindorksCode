package com.yatoooon.flow_example

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.io.IOException

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var flow: Flow<Int>
    lateinit var flowOne: Flow<String>
    lateinit var flowTwo: Flow<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFlowString()
        setupClicks()

        flowCatch()
        retry()
    }

    private fun retry() {
        CoroutineScope(Dispatchers.Main).launch {
            var currentDelay = 1000L
            val delayFactor = 2
            doLongRunningTask()
                .flowOn(Dispatchers.Default)
                .retry(retries = 3) { cause ->
                    if (cause is IOException) {
                        delay(currentDelay)
                        currentDelay = (currentDelay * delayFactor)
                        return@retry true
                    } else {
                        return@retry false
                    }
                }
                .catch { e ->
                    e.printStackTrace()
                }
                .collect {
                    Log.d(TAG, it.toString())
                }
        }
    }


    private fun doLongRunningTask(): Flow<Int> {
        return flow {
            // your code for doing a long running task
            // Added delay, random number, and exception to simulate
            delay(2000)
            val randomNumber = (0..2).random()
            if (randomNumber == 0) {
                throw IOException()
            } else if (randomNumber == 1) {
                throw IndexOutOfBoundsException()
            }
            delay(2000)
            emit(0)
        }
    }

    fun setupFlow() {

        // flow构建器
        flow = flow {
            Log.d(TAG, "Start flow")
            (0..10).forEach {
                // Emit items with 500 milliseconds delay
                delay(500)
                Log.d(TAG, "Emitting $it")
                emit(it)
            }
        }
            .map { it * it } // map操作符
            .flowOn(Dispatchers.Default) // flowOn发射的上下文  类似于 rxjava的subscribeOn
        flow = flowOf(4, 2, 5, 1, 7).onEach { delay(400) }.flowOn(Dispatchers.Default)
        flow = (1..5).asFlow().onEach { delay(300) }.flowOn(Dispatchers.Default)
        flow = channelFlow {
            (0..10).forEach {
                send(it)
            }
        }.flowOn(Dispatchers.Default)
    }

    private fun setupFlowString() {
        flowOne = flowOf("Himanshu", "Amit", "Janishar").onEach { delay(100) } // 数量一样的话  delay取决于长的
            .flowOn(Dispatchers.Default)
        flowTwo =
            flowOf("Singh", "Shekhar", "Ali").onEach { delay(4000) }.flowOn(Dispatchers.Default)
    }


    private fun setupClicks() { // 当点击时 flow才会启动   所以flow are cold
        button.setOnClickListener {
//            CoroutineScope(Dispatchers.Main).launch {//launch的上下文
//                flow.collect {//从launch的线程的收集值   //collect终端运算符  没有它flow不会启动
//                    Log.d(TAG, it.toString())
//                    text.text = it.toString()
//                }
//            }

            CoroutineScope(Dispatchers.Main).launch {
                flowOne.zip(flowTwo) // zip操作符  如果两个flow没有相同的数量，有一个flow完成   整个flow就会停止
                { firstString, secondString ->
                    "$firstString $secondString"
                }.collect {
                    Log.d(TAG, it)
                    text.text = it.toString()
                }
            }
        }
    }

    private fun flowCatch() {

        CoroutineScope(Dispatchers.Main).launch {
            (1..5).asFlow()
                .map {
                    // raise exception when the value is 3, intentionally done for the sake of example
                    check(it != 3) { "Value $it" } // raise exception
                    it * it
                }
                .catch { e ->
                    Log.d(TAG, "Caught $e")
                }
                .onCompletion {
                    Log.d(TAG, "onCompletion")
                }
                .flowOn(Dispatchers.Default)
                .collect {
                    Log.d(TAG, it.toString())
                }
        }

    }
}
