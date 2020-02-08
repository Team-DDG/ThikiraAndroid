package com.dsm.restaurant.presentation.ui.main.setting.changePwd

import androidx.lifecycle.*
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.data.error.exception.UnauthorizedException
import com.dsm.restaurant.domain.interactor.ChangePwdUseCase
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import com.dsm.restaurant.presentation.util.isValidPassword
import com.dsm.restaurant.presentation.util.isValueBlank
import kotlinx.coroutines.launch

class PasswordChangeViewModel(
    private val changePwdUseCase: ChangePwdUseCase
) : ViewModel() {

    val originalPwd = MutableLiveData<String>()
    val changePwd = MutableLiveData<String>()
    val changePwdCheck = MutableLiveData<String>()

    private val _toastEvent = SingleLiveEvent<Int>()

    val toastEvent: LiveData<Int> = _toastEvent
    private val _dismissEvent = SingleLiveEvent<Unit>()

    val dismissEvent: LiveData<Unit> = _dismissEvent

    val isChangePwdEnabled: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(originalPwd) { value = isChangePwdFilled() }
        addSource(changePwd) { value = isChangePwdFilled() }
        addSource(changePwdCheck) { value = isChangePwdFilled() }
    }

    private fun isChangePwdFilled() =
        !originalPwd.isValueBlank() && !changePwd.isValueBlank() && !changePwdCheck.isValueBlank()

    fun changePassword() = viewModelScope.launch {
        if (changePwd.value != changePwdCheck.value) {
            _toastEvent.value = R.string.fail_diff_retype
            return@launch
        }

        if (!isValidPassword(changePwd.value!!)) {
            _toastEvent.value = R.string.fail_password_invalid
            return@launch
        }

        try {
            changePwdUseCase(
                originalPwd.value ?: "",
                changePwd.value ?: ""
            )

            _dismissEvent.call()
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is UnauthorizedException -> R.string.fail_auth_password
                is ForbiddenException -> R.string.fail_forbidden
                else -> R.string.fail_internal
            }
        }
    }
}