package com.dsm.restaurant.viewModel

import com.dsm.restaurant.BaseTest
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ConflictException
import com.dsm.restaurant.domain.interactor.CheckEmailUseCase
import com.dsm.restaurant.presentation.ui.register.RegisterViewModel
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class RegisterViewModelTests : BaseTest() {

    @Mock
    private lateinit var checkEmailUseCase: CheckEmailUseCase

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun init() {
        viewModel = RegisterViewModel(checkEmailUseCase)
    }

    @Test
    fun blankEmailTest() {
        viewModel.run {
            email.value = ""

            checkEmail()

            snackbarEvent.test().assertValue(R.string.fail_blank_email)
        }
    }

    @Test
    fun invalidEmailTest() {
        viewModel.run {
            email.value = "test@naver."

            checkEmail()

            snackbarEvent.test().assertValue(R.string.fail_invalid_email)
        }
    }

    @Test
    fun checkEmailSuccessTest() = runBlockingTest {
        viewModel.run {
            email.value = "test@naver.com"

            `when`(checkEmailUseCase.invoke(email.value!!)).thenReturn(Unit)

            checkEmail()

            toastEvent.test().assertValue(R.string.success_email_check)
        }
    }

    @Test
    fun emailConflictTest() = runBlockingTest {
        viewModel.run {
            email.value = "test@naver.com"

            `when`(checkEmailUseCase.invoke(email.value!!))
                .thenThrow(ConflictException(Exception()))

            checkEmail()

            toastEvent.test().assertValue(R.string.fail_conflict_email)
        }
    }
}