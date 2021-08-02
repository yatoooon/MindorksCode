package com.yatoooon.thread_example

import android.os.Process
import java.util.concurrent.*

object DefaultExecutorSupplier {

    private val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()

    private var mForBackgroundTasks: ThreadPoolExecutor? = null
    private var mMainThreadExecutor: Executor? = null
    private var mPriorityForBackgroundTasks: PriorityThreadPoolExecutor? = null

    init {
        val backgroundPriorityThreadFactory: ThreadFactory =
            PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND)

        mForBackgroundTasks = ThreadPoolExecutor(
            NUMBER_OF_CORES * 2,
            NUMBER_OF_CORES * 2,
            60L,
            TimeUnit.SECONDS,
            LinkedBlockingQueue<Runnable>(),
            backgroundPriorityThreadFactory
        )


        mMainThreadExecutor = MainThreadExecutor()


        mPriorityForBackgroundTasks = PriorityThreadPoolExecutor(
            NUMBER_OF_CORES * 2,
            NUMBER_OF_CORES * 2,
            60L,
            TimeUnit.SECONDS,
            backgroundPriorityThreadFactory
        )

    }

    fun forBackgroundTasks(): ThreadPoolExecutor? {
        return mForBackgroundTasks
    }


    fun forMainThreadTasks(): Executor? {
        return mMainThreadExecutor
    }
}

