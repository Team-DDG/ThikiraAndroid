package com.dsm.testcomponent

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
open class BaseTest {
    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun beforeTest() {
        MockitoAnnotations.initMocks(this)
    }

    protected fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()

    protected fun <T : Any> safeEq(value: T): T = ArgumentMatchers.eq(value) ?: value
}