package com.dsm.account

import com.dsm.account.viewModel.LoginViewModel
import com.dsm.error.exception.InternalException
import com.dsm.error.exception.NotFoundException
import com.dsm.model.repository.AuthRepository
import com.dsm.testcomponent.BaseTest
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
    private lateinit var authRepository: AuthRepository

    private lateinit var viewModel: LoginViewModel

    @Before
    fun init() {
        viewModel = LoginViewModel(authRepository)
    }

    @Test
    fun isLoginClickableTest() {
        viewModel.run {
            email.value = "email@gmail.com"
            password.value = "password!"

            isLoginEnable.test().assertValue(true)

            password.value = ""

            isLoginEnable.test().assertValue(false)
        }
    }

    @Test
    fun loginSuccessTest() = runBlockingTest {
        viewModel.run {
            email.value = "dikolight203@gmail.com"
            password.value = "password!"

            `when`(
                authRepository.login(
                    hashMapOf(
                        "email" to email.value,
                        "password" to password.value
                    )
                )
            ).thenReturn(Unit)

            onClickLogin()

            navigateEvent.test().assertValue(R.id.action_loginFragment_to_mainFragment)
        }
    }

    @Test
    fun loginNotFoundAccountTest() = runBlockingTest {
        viewModel.run {
            email.value = "email@gmail.com"
            password.value = "password!"

            `when`(
                authRepository.login(
                    hashMapOf(
                        "email" to email.value,
                        "password" to password.value
                    )
                )
            ).thenThrow(NotFoundException(Exception()))

            onClickLogin()

            toastEvent.test().assertValue(R.string.fail_account_not_found)
        }
    }

    @Test
    fun loginInternalErrorTest() = runBlockingTest {
        viewModel.run {
            email.value = "email@gmail.com"
            password.value = "password!"

            `when`(
                authRepository.login(
                    hashMapOf(
                        "email" to email.value,
                        "password" to password.value
                    )
                )
            ).thenThrow(InternalException(Exception()))

            onClickLogin()

            toastEvent.test().assertValue(R.string.fail_exception_internal)
        }
    }
}