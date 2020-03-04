package com.dsm.restaurant.viewModel

import com.dsm.restaurant.BaseTest
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.data.error.exception.UnauthorizedException
import com.dsm.restaurant.domain.interactor.UnregisterUseCase
import com.dsm.restaurant.presentation.ui.setting.unregister.UnregisterViewModel
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class UnregisterViewModelTests : BaseTest() {

    @Mock
    private lateinit var unregisterUseCase: UnregisterUseCase

    private lateinit var viewModel: UnregisterViewModel

    @Before
    fun init() {
        viewModel = UnregisterViewModel(unregisterUseCase)
    }

    @Test
    fun isUnregisterEnabledTest() = runBlockingTest {
        viewModel.run {
            password.value = "PASSWORD"
            isUnregisterClickable.test().assertValue(true)

            password.value = ""
            isUnregisterClickable.test().assertValue(false)
        }
    }

    @Test
    fun unregisterSuccessTest() = runBlockingTest {
        viewModel.run {
            password.value = "PASSWORD"
            `when`(unregisterUseCase.invoke(password.value ?: "")).thenReturn(Unit)

            unregister()

            verify(unregisterUseCase).invoke(password.value ?: "")
            dismissEvent.test().assertHasValue()
        }
    }

    @Test
    fun unregisterUnauthorizedFailTest() = runBlockingTest {
        viewModel.run {
            password.value = "PASSWORD"
            `when`(unregisterUseCase.invoke(password.value ?: ""))
                .thenThrow(UnauthorizedException(Exception()))

            unregister()

            toastEvent.test().assertValue(R.string.fail_password_auth)
        }
    }

    @Test
    fun unregisterForbiddenFailTest() = runBlockingTest {
        viewModel.run {
            password.value = "PASSWORD"
            `when`(unregisterUseCase.invoke(password.value ?: ""))
                .thenThrow(ForbiddenException(Exception()))

            unregister()

            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
        }
    }
}