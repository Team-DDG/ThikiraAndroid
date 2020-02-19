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

    private val _navigateMainEvent = SingleLiveEvent<Unit>()
    val navigateMainEvent: LiveData<Unit> = _navigateMainEvent

    private val _navigateLoginEvent = SingleLiveEvent<Unit>()
    val navigateLoginEvent: LiveData<Unit> = _navigateLoginEvent

    init {
        authToken()
    }

    fun authToken() = viewModelScope.launch {
        try {
            authTokenUseCase()

            _navigateMainEvent.call()
        } catch (e: Exception) {
            _navigateLoginEvent.call()
        }
    }
}