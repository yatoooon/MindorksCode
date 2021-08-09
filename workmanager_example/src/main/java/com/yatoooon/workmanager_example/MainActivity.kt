package com.yatoooon.workmanager_example

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val yourWorkRequest = OneTimeWorkRequestBuilder<YourWorkerClass>().build()//普通任务请求
        WorkManager.getInstance(applicationContext).enqueue(yourWorkRequest)

        val myConstraints = Constraints.Builder()
//            .setRequiresDeviceIdle(true) //checks whether device should be idle for the WorkRequest to run
            .setRequiresCharging(true) //checks whether device should be charging for the WorkRequest to run
            .setRequiredNetworkType(NetworkType.CONNECTED) //checks whether device should have Network Connection
            .setRequiresBatteryNotLow(true) // checks whether device battery should have a specific level to run the work request
            .setRequiresStorageNotLow(true) // checks whether device storage should have a specific level to run the work request
            .build()

        val yourWorkRequestHaveConstraints =  //带约束的任务请求
            OneTimeWorkRequestBuilder<YourWorkerClass>()
                .setConstraints(myConstraints)
                .build()


        val yourPeriodicWorkRequest = //周期任务请求
            PeriodicWorkRequestBuilder<YourWorkerClass>(1, TimeUnit.HOURS)
                .setConstraints(myConstraints)
                .build()
        val yourWorkRequestHaveInitialDelay = //延迟任务请求
            OneTimeWorkRequestBuilder<YourWorkerClass>()
                .setInitialDelay(10, TimeUnit.MINUTES)
                .build()


        WorkManager.getInstance(applicationContext)
            .getWorkInfoByIdLiveData(yourWorkRequest.id)  //与livedata结合，观察任务状态进行操作
            .observe(this, Observer { workInfo ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    println("任务id：" + workInfo.id + "，状态" + workInfo.state)
                }
            })


        val yourWorkRequestOne = OneTimeWorkRequestBuilder<YourWorkerClass>().build()//任务请求1
        val yourWorkRequestTwo = OneTimeWorkRequestBuilder<YourWorkerClass>().build()//任务请求2
        val yourWorkRequestThree = OneTimeWorkRequestBuilder<YourWorkerClass>().build()//任务请求3
        val yourWorkRequestFour = OneTimeWorkRequestBuilder<YourWorkerClass>().build()//任务请求4
        //串联任务
        WorkManager.getInstance(applicationContext).beginWith(yourWorkRequestOne)
            .then(yourWorkRequestTwo)
            .enqueue()
        //先并联后依次
        WorkManager.getInstance(applicationContext)
            .beginWith(listOf(yourWorkRequestOne, yourWorkRequestTwo, yourWorkRequestThree))
            .then(yourWorkRequestFour)
            .enqueue()

//        WorkManager.getInstance(applicationContext).cancelWorkById(yourWorkRequest.id)  //取消任务

        //任务中输入和输出
        val userName = workDataOf("name" to "yatoooon")

        val yourWorkRequestHaveInput = OneTimeWorkRequestBuilder<YourWorkerClass>()
            .setInputData(userName)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(yourWorkRequestHaveInput)
        WorkManager.getInstance(applicationContext)
            .getWorkInfoByIdLiveData(yourWorkRequestHaveInput.id)  //与livedata结合，观察任务状态进行操作
            .observe(this, Observer { workInfo ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    println("成功返回数据：" + workInfo.outputData)
                }
            })


    }
}


class YourWorkerClass(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        val name = inputData.getString("name")
        // Create the output of the work
        // Return the output
        val result: Data?
        result = if (name == "yatoooon") {
            workDataOf("result" to "hello--$name")
        } else {
            workDataOf("result" to "fail--$name")
        }
        return Result.success(result)
    }
}