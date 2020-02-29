package com.dsm.account.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsm.account.R
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.model.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _navigateEvent = SingleLiveEvent<Int>()
    val navigateEvent: LiveData<Int> = _navigateEvent

    init {
        authToken()
    }

    private fun authToken() = viewModelScope.launch {
        try {
            delay(1000)
            authRepository.authToken()

            _navigateEvent.value = R.id.action_splashFragment_to_mainFragment
        } catch (e: Exception) {
            _navigateEvent.value = R.id.action_splashFragment_to_loginFragment
        }
    }
}