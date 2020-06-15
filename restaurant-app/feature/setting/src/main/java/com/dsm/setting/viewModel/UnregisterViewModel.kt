package com.dsm.setting.viewModel

import androidx.lifecycle.*
import com.dsm.androidcomponent.R
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.model.repository.AccountRepository
import com.dsm.model.repository.AuthRepository
import com.example.error.exception.ForbiddenException
import com.example.error.exception.UnauthorizedException
import kotlinx.coroutines.launch

class UnregisterViewModel(
    private val authRepository: AuthRepository,
    private val accountRepository: AccountRepository
) : ViewModel() {

    // two-way binding
    val password = MutableLiveData<String>()

    val isUnregisterEnable: LiveData<Boolean> = Transformations.map(password) { it != "" }

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _navigateEvent = SingleLiveEvent<Int>()
    val navigateEvent: LiveData<Int> = _navigateEvent

    fun onClickUnregister() = viewModelScope.launch {
        try {
            authRepository.confirmPassword(password.value!!)
            accountRepository.unregister()

            _navigateEvent.value = R.id.action_unregisterDialog_to_nav_graph
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is UnauthorizedException -> com.dsm.setting.R.string.fail_password_auth
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }
}