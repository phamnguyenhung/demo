package com.example.myapplication

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.LiveData
import org.junit.rules.TestWatcher
import org.junit.runner.Description

val <T> LiveData<T>.awaitValue: T?
    get() {
        var value: T? = null
        observeForever {
            value = this.value
        }
        return value
    }

class LiveDataRule : TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

        })
    }

    override fun finished(description: Description?) {
        super.finished(description)
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}