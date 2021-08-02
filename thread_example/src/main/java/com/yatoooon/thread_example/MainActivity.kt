package com.yatoooon.thread_example

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //(1)主线程使用handler
    private var handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            progress_horizontal.progress = msg.arg1
        }
    }

    //(2)子线程使用handler
    class LooperThread : Thread() {
        var mHandler: Handler? = null
        override fun run() {
            Looper.prepare()
            mHandler = object : Handler(Looper.myLooper()!!) {
                override fun handleMessage(msg: Message) {
                    println("LooperThread:" + msg.obj)
                }
            }
            Looper.loop()
        }


    }

    //（3）一般不提倡自己写（2），google 给出了HandlerThread
    class MyHandlerThread(name: String?) : HandlerThread(name) {
        var handler: Handler? = null
        override fun onLooperPrepared() {
            handler = object : Handler(looper) {
                override fun handleMessage(msg: Message) {
                    println("Name:" + Thread.currentThread().name + ",MyHandlerThread:" + msg.obj)
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val looperThread = LooperThread()
        looperThread.start()

        val myHandlerThread = MyHandlerThread("MyHandlerThread1")
        myHandlerThread.start()

        //相同的效果
        val handlerThread = HandlerThread("MyHandlerThread2")
        handlerThread.start()
        val hhh = object : Handler(handlerThread.looper) {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                println("Name:" + Thread.currentThread().name + ",MyHandlerThread:" + msg.obj)
            }
        }

        Thread() {
            for (i in 1..100) {
                //(1)
                Thread.sleep(50)
                val message = Message()
                message.arg1 = i
                handler.sendMessage(message)
                //相同的效果
                Handler(Looper.getMainLooper()).post {
                    tv_progress.text = i.toString()
                }

                //(2)
                looperThread.mHandler?.sendMessage(Message().apply { obj = i })


                //(3)
                myHandlerThread.handler?.sendMessage(Message().apply { obj = i })

                //相同的效果
                hhh.sendMessage(Message().apply { obj = i })

            }
        }.start()


//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//            int corePoolSize,    //池中保留的最小线程数
//            int maximumPoolSize, //池中允许的最大线程数
//            long keepAliveTime, //线程数大于核心数时     空闲线程超过这个时间就会终止
//            TimeUnit unit, //keepAliveTime的时间单位
//            BlockingQueue<Runnable> workQueue  //任务队列，只保存可运行的任务  阻塞队列
//        );

        val future = DefaultExecutorSupplier.forBackgroundTasks()
            ?.submit(Runnable {

            })
        future?.cancel(true)  //取消

    }

    fun doSomeBackgroundWork() {
        DefaultExecutorSupplier.forBackgroundTasks()?.execute(Runnable {

        })
    }


    //
    fun doSomeMainThreadWork() {
        DefaultExecutorSupplier.forMainThreadTasks()?.execute(Runnable {

        })
    }


    fun doSomeTaskAtHighPriority() {
        DefaultExecutorSupplier.forBackgroundTasks()
            ?.submit(object : PriorityRunnable(Priority.HIGH) {
                override fun run() {

                }
            })
    }
}
