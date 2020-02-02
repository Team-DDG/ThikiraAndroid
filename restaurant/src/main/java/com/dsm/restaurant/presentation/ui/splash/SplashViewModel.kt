package com.dsm.restaurant.presentation.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsm.restaurant.domain.interactor.AuthTokenUseCase
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch

class SplashViewModel(
    private val authTokenUseCase: AuthTokenUseCase
) : ViewModel() {

    private val _navigateToMainEvent = SingleLiveEvent<Unit>()
    val navigateToMainEvent: LiveData<Unit> = _navigateToMainEvent

    private val _navigateToLoginEvent = SingleLiveEvent<Unit>()
    val navigateToLoginEvent: LiveData<Unit> = _navigateToLoginEvent

    fun authToken() = viewModelScope.launch {
        try {
            authTokenUseCase()

            _navigateToMainEvent.call()
        } catch (e: Exception) {
            _navigateToLoginEvent.call()
        }
    }
}