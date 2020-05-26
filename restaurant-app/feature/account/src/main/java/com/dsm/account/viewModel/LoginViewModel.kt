package com.dsm.account.viewModel

import androidx.lifecycle.*
import com.dsm.account.R
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.androidcomponent.ext.isValidEmail
import com.dsm.error.exception.NotFoundException
import com.dsm.model.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    // two-way binding
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val isLoginEnable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        listOf(email, password).forEach {
            addSource(it) { value = !(email.value.isNullOrBlank() || password.value.isNullOrBlank()) }
        }
    }

    private val _navigateEvent = SingleLiveEvent<Int>()
    val navigateEvent: LiveData<Int> = _navigateEvent

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _hideKeyEvent = SingleLiveEvent<Unit>()
    val hideKeyEvent: LiveData<Unit> = _hideKeyEvent

    fun onClickLogin() = viewModelScope.launch {
        if (isValidEmail(email.value)) {
            _toastEvent.value = R.string.fail_email_invalid
            return@launch
        }

        try {
            authRepository.login(
                hashMapOf(
                    "email" to email.value,
                    "password" to password.value
                )
            )

            _navigateEvent.value = R.id.action_loginFragment_to_mainFragment
            _hideKeyEvent.call()
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is NotFoundException -> R.string.fail_account_not_found
                else -> R.string.fail_exception_internal
            }
        }
    }
}