package com.dsm.restaurant.viewModel

import com.dsm.restaurant.BaseTest
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.data.error.exception.UnauthorizedException
import com.dsm.restaurant.domain.interactor.ChangePasswordUseCase
import com.dsm.restaurant.presentation.ui.setting.changePwd.PasswordChangeViewModel
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
    private lateinit var changePasswordUseCase: ChangePasswordUseCase

    private lateinit var viewModel: PasswordChangeViewModel

    @Before
    fun init() {
        viewModel = PasswordChangeViewModel(changePasswordUseCase)
    }

    @Test
    fun isChangePwdEnabledTest() {
        viewModel.run {
            originalPassword.value = "ORIGINAL_PASSWORD"
            newPassword.value = "PASSWORD"
            newPasswordReType.value = "PASSWORD"
            isPasswordChangeClickable.test().assertValue(true)

            newPasswordReType.value = ""
            isPasswordChangeClickable.test().assertValue(false)
        }
    }

    @Test
    fun passwordDiffTest() {
        viewModel.run {
            originalPassword.value = "ORIGINAL_PASSWORD"
            newPassword.value = "PASSWORD"
            newPasswordReType.value = "DIFF_PASSWORD"

            onClickChangePassword()

            toastEvent.test().assertValue(R.string.fail_re_type_different)
        }
    }

    @Test
    fun passwordInvalidTest() {
        viewModel.run {
            originalPassword.value = "ORIGINAL_PASSWORD"
            newPassword.value = "invalid"
            newPasswordReType.value = "invalid"

            onClickChangePassword()

            toastEvent.test().assertValue(R.string.fail_password_invalid)
        }
    }

    @Test
    fun changePasswordSuccessTest() = runBlockingTest {
        viewModel.run {
            originalPassword.value = "ORIGINAL_PASSWORD"
            newPassword.value = "password!"
            newPasswordReType.value = "password!"

            `when`(
                changePasswordUseCase.invoke(
                    originalPassword.value ?: "",
                    newPassword.value ?: ""
                )
            ).thenReturn(Unit)

            onClickChangePassword()

            dismissEvent.test().assertHasValue()
        }
    }

    @Test
    fun changePasswordUnauthorizedTest() = runBlockingTest {
        viewModel.run {
            originalPassword.value = "ORIGINAL_PASSWORD"
            newPassword.value = "password!"
            newPasswordReType.value = "password!"

            `when`(
                changePasswordUseCase.invoke(
                    originalPassword.value ?: "",
                    newPassword.value ?: ""
                )
            ).thenThrow(UnauthorizedException(Exception()))

            onClickChangePassword()

            toastEvent.test().assertValue(R.string.fail_password_auth)
        }
    }

    @Test
    fun changePasswordForbiddenTest() = runBlockingTest {
        viewModel.run {
            originalPassword.value = "ORIGINAL_PASSWORD"
            newPassword.value = "password!"
            newPasswordReType.value = "password!"

            `when`(
                changePasswordUseCase.invoke(
                    originalPassword.value ?: "",
                    newPassword.value ?: ""
                )
            ).thenThrow(ForbiddenException(Exception()))

            onClickChangePassword()

            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
        }
    }
}