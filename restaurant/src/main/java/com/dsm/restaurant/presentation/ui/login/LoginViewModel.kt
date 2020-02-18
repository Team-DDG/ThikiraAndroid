package com.dsm.restaurant.presentation.ui.login

import androidx.lifecycle.*
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.NotFoundException
import com.dsm.restaurant.domain.interactor.LoginUseCase
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    // two-way binding
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _navigateMainEvent = SingleLiveEvent<Unit>()
    val navigateMainEvent: LiveData<Unit> = _navigateMainEvent

    private val _hideKeyboardEvent = SingleLiveEvent<Unit>()
    val hideKeyboardEvent: LiveData<Unit> = _hideKeyboardEvent

    fun onClickLogin() = viewModelScope.launch {
        try {
            loginUseCase(
                hashMapOf(
                    "email" to email.value,
                    "password" to password.value
                )
            )

            _navigateMainEvent.call()
            _hideKeyboardEvent.call()
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is NotFoundException -> R.string.fail_account_not_found
                else -> R.string.fail_exception_internal
            }
        }
    }

    /**
     * button enable
     */
    val isLoginClickable = MediatorLiveData<Boolean>().apply {
        addSource(email) { value = !email.value.isNullOrBlank() && !password.value.isNullOrBlank() }
        addSource(password) { value = !email.value.isNullOrBlank() && !password.value.isNullOrBlank() }
    }
}