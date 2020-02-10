package com.dsm.restaurant.presentation.ui.main.setting.unregister

import androidx.lifecycle.*
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.data.error.exception.UnauthorizedException
import com.dsm.restaurant.domain.interactor.UnregisterUseCase
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch

class UnregisterViewModel(
    private val unregisterUseCase: UnregisterUseCase
) : ViewModel() {

    val password = MutableLiveData<String>("")

    val isUnregisterEnabled: LiveData<Boolean> = Transformations.map(password) { it != "" }

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _dismissEvent = SingleLiveEvent<Unit>()
    val dismissEvent: LiveData<Unit> = _dismissEvent

    fun unregister() = viewModelScope.launch {
        try {
            unregisterUseCase(password.value ?: "")

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