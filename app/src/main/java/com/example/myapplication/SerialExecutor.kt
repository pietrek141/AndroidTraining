package com.example.myapplication

import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

class SerialExecutor(private val executor: ExecutorService) :
    Executor {
    private val tasks: Queue<Runnable> = LinkedList()
    var active: Runnable? = null

    @Synchronized
    override fun execute(r: Runnable) {
//        executor.submit(r)
        tasks.offer(Runnable {
            try {
                r.run()
            } finally {
                scheduleNext()
            }
        })
        if (active == null) {
            scheduleNext()
        }
    }

    fun executeCallable(r: Callable<Credentials?>): Future<Credentials?> = executor.submit(r)


    @Synchronized
    protected fun scheduleNext() {
        if (tasks.poll().also { active = it } != null) {
            executor.execute(active)
        }
    }

}