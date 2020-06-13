package com.dsm.setting.viewModel

import androidx.lifecycle.*
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.androidcomponent.ext.isValidPassword
import com.dsm.model.repository.AccountRepository
import com.dsm.model.repository.AuthRepository
import com.dsm.setting.R
import com.example.error.exception.ForbiddenException
import com.example.error.exception.UnauthorizedException
import kotlinx.coroutines.launch

class PasswordChangeViewModel(
    private val authRepository: AuthRepository,
    private val accountRepository: AccountRepository
) : ViewModel() {

    // two-way binding
    val originalPassword = MutableLiveData<String>()
    val newPassword = MutableLiveData<String>()
    val newPasswordRetype = MutableLiveData<String>()

    val isChangePasswordEnable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        listOf(originalPassword, newPassword, newPasswordRetype).forEach {
            addSource(it) {
                value = !(originalPassword.value.isNullOrBlank() || newPassword.value.isNullOrBlank()
                        || newPasswordRetype.value.isNullOrBlank())
            }
        }
    }

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _popEvent = SingleLiveEvent<Unit>()
    val popEvent: LiveData<Unit> = _popEvent

    fun onClickChangePassword() {
        if (newPassword.value != newPasswordRetype.value) {
            _toastEvent.value = R.string.fail_re_type_different
            return
        }

        if (!isValidPassword(newPassword.value!!)) {
            _toastEvent.value = R.string.fail_password_invalid
            return
        }

        confirmAndChangePassword(originalPassword.value!!, newPassword.value!!)
    }

    private fun confirmAndChangePassword(originalPassword: String, newPassword: String) = viewModelScope.launch {
        try {
            authRepository.confirmPassword(originalPassword)
            accountRepository.changePassword(newPassword)

            _popEvent.call()
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is UnauthorizedException -> R.string.fail_password_auth
                is ForbiddenException -> com.dsm.androidcomponent.R.string.fail_exception_forbidden
                else -> com.dsm.androidcomponent.R.string.fail_exception_internal
            }
        }
    }
}