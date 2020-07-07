package com.example.account.viewmodel

import androidx.lifecycle.*
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.androidcomponent.ext.isValidEmail
import com.example.model.repository.AuthRepository
import com.example.account.R
import com.example.error.exception.NotFoundException
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    val isLoginEnable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        listOf(email, password).forEach {
            addSource(it) { value = !(email.value.isNullOrBlank() || password.value.isNullOrBlank()) }
        }
    }

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _hideKeyEvent = SingleLiveEvent<Unit>()
    val hideKeyEvent: LiveData<Unit> = _hideKeyEvent

    private val _navigateMainEvent = SingleLiveEvent<Unit>()
    val navigateMainEvent: LiveData<Unit> = _navigateMainEvent

    private val _navigateSignupEvent = SingleLiveEvent<Unit>()
    val navigateSignupEvent: LiveData<Unit> = _navigateSignupEvent

    private val _finishLoginEvent = SingleLiveEvent<Unit>()
    val finishLoginEvent: LiveData<Unit> = _finishLoginEvent

    fun onClickLogin() = viewModelScope.launch {
        try {
            if (!isValidEmail(email.value)) {
                _toastEvent.value = R.string.fail_email_invalid
                return@launch
            }

            authRepository.login(
                hashMapOf(
                    "email" to email.value,
                    "password" to password.value
                )
            )

            _navigateMainEvent.call()
            _finishLoginEvent.call()
            _hideKeyEvent.call()
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is NotFoundException -> R.string.fail_account_not_found
                else -> R.string.fail_exception_internal
            }
        }
    }

    fun onClickSignup() {
        _navigateSignupEvent.call()
    }
}