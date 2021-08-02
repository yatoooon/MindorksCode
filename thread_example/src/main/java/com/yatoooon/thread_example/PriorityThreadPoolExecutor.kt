package com.yatoooon.thread_example

import java.util.concurrent.*

class PriorityThreadPoolExecutor(
    corePoolSize: Int, maximumPoolSize: Int, keepAliveTime: Long,
    unit: TimeUnit?, threadFactory: ThreadFactory?
) :
    ThreadPoolExecutor(
        corePoolSize,
        maximumPoolSize,
        keepAliveTime,
        unit,
        PriorityBlockingQueue<Runnable>(),
        threadFactory
    ) {
    override fun submit(task: Runnable): Future<*> {
        val futureTask = PriorityFutureTask(task as PriorityRunnable)
        execute(futureTask)
        return futureTask
    }

    class PriorityFutureTask(private val priorityRunnable: PriorityRunnable) :
        FutureTask<PriorityRunnable>(priorityRunnable, null),
        Comparable<PriorityFutureTask> {

        override operator fun compareTo(other: PriorityFutureTask): Int {
            val p1 = priorityRunnable.priority
            val p2 = other.priorityRunnable.priority
            return p2.ordinal - p1.ordinal
        }
    }
}
