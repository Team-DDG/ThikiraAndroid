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
            originalPwd.value = "ORIGINAL_PASSWORD"
            changePwd.value = "PASSWORD"
            changePwdCheck.value = "PASSWORD"
            isChangePwdEnabled.test().assertValue(true)

            changePwdCheck.value = ""
            isChangePwdEnabled.test().assertValue(false)
        }
    }

    @Test
    fun passwordDiffTest() {
        viewModel.run {
            originalPwd.value = "ORIGINAL_PASSWORD"
            changePwd.value = "PASSWORD"
            changePwdCheck.value = "DIFF_PASSWORD"

            changePassword()

            toastEvent.test().assertValue(R.string.fail_re_type_different)
        }
    }

    @Test
    fun passwordInvalidTest() {
        viewModel.run {
            originalPwd.value = "ORIGINAL_PASSWORD"
            changePwd.value = "invalid"
            changePwdCheck.value = "invalid"

            changePassword()

            toastEvent.test().assertValue(R.string.fail_password_invalid)
        }
    }

    @Test
    fun changePasswordSuccessTest() = runBlockingTest {
        viewModel.run {
            originalPwd.value = "ORIGINAL_PASSWORD"
            changePwd.value = "password!"
            changePwdCheck.value = "password!"

            `when`(
                changePasswordUseCase.invoke(
                    originalPwd.value ?: "",
                    changePwd.value ?: ""
                )
            ).thenReturn(Unit)

            changePassword()

            dismissEvent.test().assertHasValue()
        }
    }

    @Test
    fun changePasswordUnauthorizedTest() = runBlockingTest {
        viewModel.run {
            originalPwd.value = "ORIGINAL_PASSWORD"
            changePwd.value = "password!"
            changePwdCheck.value = "password!"

            `when`(
                changePasswordUseCase.invoke(
                    originalPwd.value ?: "",
                    changePwd.value ?: ""
                )
            ).thenThrow(UnauthorizedException(Exception()))

            changePassword()

            toastEvent.test().assertValue(R.string.fail_password_auth)
        }
    }

    @Test
    fun changePasswordForbiddenTest() = runBlockingTest {
        viewModel.run {
            originalPwd.value = "ORIGINAL_PASSWORD"
            changePwd.value = "password!"
            changePwdCheck.value = "password!"

            `when`(
                changePasswordUseCase.invoke(
                    originalPwd.value ?: "",
                    changePwd.value ?: ""
                )
            ).thenThrow(ForbiddenException(Exception()))

            changePassword()

            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
        }
    }
}