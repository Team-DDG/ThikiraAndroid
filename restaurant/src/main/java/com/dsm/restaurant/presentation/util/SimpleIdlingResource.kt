package com.dsm.restaurant.presentation.util

import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicInteger

class SimpleIdlingResource : IdlingResource {

    private val counter = AtomicInteger(0)

    private var callback: IdlingResource.ResourceCallback? = null

    override fun getName(): String = this.javaClass.name

    override fun isIdleNow(): Boolean = counter.get() == 0

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }

    fun increment() = counter.getAndIncrement()

    fun decrement() {
        val counterVal = counter.decrementAndGet()
        if (counterVal == 0) {
            callback?.onTransitionToIdle()
        } else if(counterVal < 0) {
            throw IllegalStateException("Counter has value lower than 0")
        }
    }
}