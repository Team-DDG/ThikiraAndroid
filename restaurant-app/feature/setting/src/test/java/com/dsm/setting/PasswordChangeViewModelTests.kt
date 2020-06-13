package com.dsm.setting

import com.dsm.model.repository.AccountRepository
import com.dsm.model.repository.AuthRepository
import com.dsm.setting.viewModel.PasswordChangeViewModel
import com.dsm.testcomponent.BaseTest
import com.example.error.exception.ForbiddenException
import com.example.error.exception.UnauthorizedException
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class PasswordChangeViewModelTests : BaseTest() {

    @Mock
    private lateinit var accountRepository: AccountRepository

    @Mock
    private lateinit var authRepository: AuthRepository

    private lateinit var viewModel: PasswordChangeViewModel

    @Before
    fun init() {
        viewModel = PasswordChangeViewModel(authRepository, accountRepository)
    }

    @Test
    fun onTypedPasswordDifferentTest() {
        viewModel.run {
            newPassword.value = "aaaaaa!"
            newPasswordRetype.value = "DIFFERENT"

            onClickChangePassword()

            toastEvent.test().assertValue(R.string.fail_re_type_different)
        }
    }

    @Test
    fun onPasswordInvalidTest() {
        viewModel.run {
            newPassword.value = "aaaaaa"
            newPasswordRetype.value = "aaaaaa"

            onClickChangePassword()

            toastEvent.test().assertValue(R.string.fail_password_invalid)
        }
    }

    @Test
    fun authPasswordFailedTest() = runBlockingTest {
        viewModel.run {
            originalPassword.value = "aaaaaaa!"
            newPassword.value = "aaaaaa!"
            newPasswordRetype.value = "aaaaaa!"
            `when`(authRepository.confirmPassword(originalPassword.value!!))
                .thenThrow(UnauthorizedException(Exception()))

            onClickChangePassword()

            toastEvent.test().assertValue(R.string.fail_password_auth)
            popEvent.test().assertNoValue()
        }
    }

    @Test
    fun changePasswordForbiddenTest() = runBlockingTest {
        viewModel.run {
            originalPassword.value = "aaaaaaa!"
            newPassword.value = "aaaaaa!"
            newPasswordRetype.value = "aaaaaa!"
            `when`(authRepository.confirmPassword(originalPassword.value!!)).thenReturn(Unit)
            `when`(accountRepository.changePassword(newPassword.value!!))
                .thenThrow(ForbiddenException(Exception()))

            onClickChangePassword()

            toastEvent.test().assertValue(com.dsm.androidcomponent.R.string.fail_exception_forbidden)
            popEvent.test().assertNoValue()
        }
    }

    @Test
    fun changePasswordSuccessTest() = runBlockingTest {
        viewModel.run {
            originalPassword.value = "aaaaaaa!"
            newPassword.value = "aaaaaa!"
            newPasswordRetype.value = "aaaaaa!"
            `when`(authRepository.confirmPassword(originalPassword.value!!)).thenReturn(Unit)
            `when`(accountRepository.changePassword(newPassword.value!!)).thenReturn(Unit)

            onClickChangePassword()

            toastEvent.test().assertNoValue()
            popEvent.test().assertHasValue()
        }
    }
}