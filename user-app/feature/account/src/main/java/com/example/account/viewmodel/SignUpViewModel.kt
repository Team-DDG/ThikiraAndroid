package com.example.account.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dsm.androidcomponent.SingleLiveEvent

class SignUpViewModel: ViewModel() {
    val email = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val certificationNum = MutableLiveData<String>()

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent
}