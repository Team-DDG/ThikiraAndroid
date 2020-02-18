package com.dsm.restaurant.presentation.ui.setting.changePwd

import androidx.lifecycle.*
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.data.error.exception.UnauthorizedException
import com.dsm.restaurant.domain.interactor.ChangePasswordUseCase
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import com.dsm.restaurant.presentation.util.isValidPassword
import kotlinx.coroutines.launch

class PasswordChangeViewModel(
    private val changePasswordUseCase: ChangePasswordUseCase
) : ViewModel() {

    val originalPassword = MutableLiveData<String>()
    val newPassword = MutableLiveData<String>()
    val newPasswordReType = MutableLiveData<String>()

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _dismissEvent = SingleLiveEvent<Unit>()
    val dismissEvent: LiveData<Unit> = _dismissEvent

    val isPasswordChangeClickable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(originalPassword) { value = isPasswordChangeFilled() }
        addSource(newPassword) { value = isPasswordChangeFilled() }
        addSource(newPasswordReType) { value = isPasswordChangeFilled() }
    }

    private fun isPasswordChangeFilled() =
        !originalPassword.value.isNullOrBlank() && !newPassword.value.isNullOrBlank() && !newPasswordReType.value.isNullOrBlank()

    fun onClickChangePassword() {
        if (newPassword.value != newPasswordReType.value) {
            _toastEvent.value = R.string.fail_re_type_different
            return
        }

        if (!isValidPassword(newPassword.value!!)) {
            _toastEvent.value = R.string.fail_password_invalid
            return
        }

        changePassword()
    }

    private fun changePassword() = viewModelScope.launch {
        try {
            changePasswordUseCase(
                originalPassword.value ?: "",
                newPassword.value ?: ""
            )

            _dismissEvent.call()
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is UnauthorizedException -> R.string.fail_password_auth
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }
}