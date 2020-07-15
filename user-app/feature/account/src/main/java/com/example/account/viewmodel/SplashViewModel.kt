package com.example.account.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsm.androidcomponent.SingleLiveEvent
import com.example.model.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    init {
        checkLogin()
    }

    private val _startMainEvent = SingleLiveEvent<Unit>()
    val startMainEvent: LiveData<Unit> = _startMainEvent

    private val _startLoginEvent = SingleLiveEvent<Unit>()
    val startLoginEvent: LiveData<Unit> = _startLoginEvent

    private val _finishActivityEvent = SingleLiveEvent<Unit>()
    val finishActivityEvent = _finishActivityEvent

    private fun checkLogin() = viewModelScope.launch {
        delay(1500)
        if (authRepository.isLoggedIn()) _startMainEvent.call()
        else _startLoginEvent.call()

        _finishActivityEvent.call()
    }
}