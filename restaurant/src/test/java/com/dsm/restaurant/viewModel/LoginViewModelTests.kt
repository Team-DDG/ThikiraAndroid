package com.dsm.restaurant.viewModel

import com.dsm.restaurant.BaseTest
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.InternalException
import com.dsm.restaurant.data.error.exception.NotFoundException
import com.dsm.restaurant.domain.interactor.LoginUseCase
import com.dsm.restaurant.presentation.ui.login.LoginViewModel
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class LoginViewModelTests : BaseTest() {

    @Mock
    private lateinit var loginUseCase: LoginUseCase

    private lateinit var viewModel: LoginViewModel

    @Before
    fun init() {
        viewModel = LoginViewModel(loginUseCase)
    }

    @Test
    fun isLoginClickableTest() {
        viewModel.run {
            email.value = "email@gmail.com"
            password.value = "password!"

            isLoginClickable.test().assertValue(true)

            password.value = ""

            isLoginClickable.test().assertValue(false)
        }
    }

    @Test
    fun loginSuccessTest() = runBlockingTest {
        viewModel.run {
            email.value = "email@gmail.com"
            password.value = "password!"

            `when`(
                loginUseCase.invoke(
                    hashMapOf(
                        "email" to email.value,
                        "password" to password.value
                    )
                )
            ).thenReturn(Unit)

            login()

            navigateMainEvent.test().assertHasValue()
            hideKeyboardEvent.test().assertHasValue()
        }
    }

    @Test
    fun loginNotFoundAccountTest() = runBlockingTest {
        viewModel.run {
            email.value = "email@gmail.com"
            password.value = "password!"

            `when`(
                loginUseCase.invoke(
                    hashMapOf(
                        "email" to email.value,
                        "password" to password.value
                    )
                )
            ).thenThrow(NotFoundException(Exception()))

            login()

            toastEvent.test().assertValue(R.string.fail_login_not_found)
        }
    }

    @Test
    fun loginInternalErrorTest() = runBlockingTest {
        viewModel.run {
            email.value = "email@gmail.com"
            password.value = "password!"

            `when`(
                loginUseCase.invoke(
                    hashMapOf(
                        "email" to email.value,
                        "password" to password.value
                    )
                )
            ).thenThrow(InternalException(Exception()))

            login()

            toastEvent.test().assertValue(R.string.fail_internal)
        }
    }
}