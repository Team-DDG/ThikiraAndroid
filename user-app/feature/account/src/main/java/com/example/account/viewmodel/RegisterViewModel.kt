package com.example.account.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.androidcomponent.ext.isValidEmail
import com.example.account.R
import com.example.error.exception.ConflictException
import com.example.firebase.FirebaseAuthSource

class RegisterViewModel(
    private val firebaseAuthSource: FirebaseAuthSource
): ViewModel() {
    val email = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val certificationNum = MutableLiveData<String>()

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _isNextEnable = MutableLiveData<Boolean>(false)
    val isNextEnable: LiveData<Boolean> = _isNextEnable

    private val _authCode = MutableLiveData<String>()
    val authCode: LiveData<String> = _authCode

    val isStartVerifyEnable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        value = !phone.value.isNullOrBlank()
    }

    val isVerifyEnable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        value = !certificationNum.value.isNullOrBlank()
    }

    fun onClickStartVerify(activity: Activity) {
        firebaseAuthSource.userAuthWithPhone(
            phone.value!!,
            activity,
            { code -> onVerificationCompleted(code) },
            { onVerificationFailed() })
    }

    private fun onVerificationCompleted(code: String) {
        _toastEvent.value = R.string.success_verify_phone
        _authCode.value = code
        _isNextEnable.value = true
    }

    private fun onVerificationFailed() {
        _toastEvent.value = R.string.fail_verify_phone
    }

    fun onClickDuplicationCheck() {
        val currentEmail = email.value

        if(currentEmail != null || currentEmail.isNullOrBlank()){
            _toastEvent.value = R.string.fail_email_blank
            return
        }

        if (!isValidEmail(currentEmail)) {
            _toastEvent.value = R.string.fail_email_invalid
            return
        }

        checkEmailDuplication(currentEmail)
    }

    private fun checkEmailDuplication(email: String) {
        try {

        } catch (e: Exception){
            _toastEvent.value = when(e) {
                is ConflictException -> R.string.fail_email_conflict
                else -> R.string.fail_exception_internal
            }
        }
    }
}