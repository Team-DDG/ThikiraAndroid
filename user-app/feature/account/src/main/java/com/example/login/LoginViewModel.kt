package com.example.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dsm.androidcomponent.SingleLiveEvent

class LoginViewModel : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

   private val _navigateEvent = SingleLiveEvent<Int>()
    val navigateEvent: LiveData<Int> = _navigateEvent

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _hideKeyboardEvent = SingleLiveEvent<Int>()
    val hideKeyboardEvent: LiveData<Int> = _hideKeyboardEvent

    fun onClickLogin() {
        //TODO add code for login
    }
}