package com.dsm.restaurant.presentation.utilTesting

object EspressoIdlingResource {

    val countingIdlingResource = SimpleIdlingResource()

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}

inline fun <T> wrapEspressoIdlingResource(function: () -> T): T {
    EspressoIdlingResource.increment()

    return try {
        function()
    } finally {
        EspressoIdlingResource.decrement()
    }
}