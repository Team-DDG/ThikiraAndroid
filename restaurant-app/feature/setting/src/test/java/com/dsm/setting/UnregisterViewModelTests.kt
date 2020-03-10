package com.dsm.setting

import com.dsm.error.exception.ForbiddenException
import com.dsm.error.exception.UnauthorizedException
import com.dsm.model.repository.AccountRepository
import com.dsm.model.repository.AuthRepository
import com.dsm.setting.viewModel.UnregisterViewModel
import com.dsm.testcomponent.BaseTest
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verifyNoInteractions

@ExperimentalCoroutinesApi
class UnregisterViewModelTests : BaseTest() {

    @Mock
    private lateinit var authRepository: AuthRepository

    @Mock
    private lateinit var accountRepository: AccountRepository

    private lateinit var viewModel: UnregisterViewModel

    @Before
    fun init() {
        viewModel = UnregisterViewModel(authRepository, accountRepository)
    }

    @Test
    fun isUnregisterEnableTest() {
        viewModel.run {
            password.value = "Hello"
            isUnregisterEnable.test().assertValue(true)

            password.value = ""
            isUnregisterEnable.test().assertValue(false)
        }
    }

    @Test
    fun confirmPasswordUnauthorizedTest() = runBlockingTest {
        viewModel.run {
            password.value = "hello!!"
            `when`(authRepository.confirmPassword(password.value!!))
                .thenThrow(UnauthorizedException(Exception()))

            onClickUnregister()

            toastEvent.test().assertValue(R.string.fail_password_auth)
            navigateEvent.test().assertNoValue()
            verifyNoInteractions(accountRepository)
        }
    }

    @Test
    fun unregisterForbiddenTest() = runBlockingTest {
        viewModel.run {
            password.value = "hello!!"
            `when`(authRepository.confirmPassword(password.value!!)).thenReturn(Unit)
            `when`(accountRepository.unregister())
                .thenThrow(ForbiddenException(Exception()))

            onClickUnregister()

            toastEvent.test().assertValue(com.dsm.androidcomponent.R.string.fail_exception_forbidden)
            navigateEvent.test().assertNoValue()
        }
    }

    @Test
    fun unregisterSuccessTest() = runBlockingTest {
        viewModel.run {
            password.value = "hello!!"
            `when`(authRepository.confirmPassword(password.value!!)).thenReturn(Unit)
            `when`(accountRepository.unregister()).thenReturn(Unit)

            onClickUnregister()

            toastEvent.test().assertNoValue()
            navigateEvent.test().assertValue(com.dsm.androidcomponent.R.id.action_unregisterDialog_to_loginFragment)
        }
    }
}