package com.example.account.viewmodel

import android.app.Activity
import androidx.lifecycle.*
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.androidcomponent.ext.isValidEmail
import com.example.account.R
import com.example.error.exception.ConflictException
import com.example.firebase.FirebaseAuthSource
import com.example.model.repository.AuthRepository
import kotlinx.coroutines.launch

class SignupViewModel(
    private val firebaseAuthSource: FirebaseAuthSource,
    private val authRepository: AuthRepository
) : ViewModel() {
    private var firebaseAuthCode: String = ""

    val email = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val authCode = MutableLiveData<String>()

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _isNextEnable = MutableLiveData<Boolean>(false)
    val isNextEnable: LiveData<Boolean> = _isNextEnable

    private val _navigateSignup2Event = SingleLiveEvent<Unit>()
    val navigateSignup2Event: LiveData<Unit> = _navigateSignup2Event

    val isStartVerifyEnable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(phone) { value = !phone.value.isNullOrBlank() }
        addSource(isNextEnable) { value = !isNextEnable.value!! }
    }

    val isVerifyEnable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(authCode) { value = !authCode.value?.isBlank()!! }
        addSource(isNextEnable) { value = !isNextEnable.value!! }
    }

    fun onClickStartVerify(activity: Activity) {
        firebaseAuthSource.userAuthWithPhone(
            phone.value!!,
            activity,
            { code -> onVerificationCompleted(code) },
            { onVerificationFailed() })
    }

    private fun onVerificationCompleted(code: String) {
        firebaseAuthCode = code
        authCode.value = code
    }

    private fun onVerificationFailed() {
        _toastEvent.value = R.string.fail_verify_phone
    }

    fun onClickVerify() {
        if(firebaseAuthCode == authCode.value) {
            _toastEvent.value = R.string.success_verify_phone
            _isNextEnable.value = true
        } else {
            _toastEvent.value = R.string.fail_verify_phone
        }
    }

    fun onClickNext() {
        _navigateSignup2Event.call()
    }

    fun onClickDuplicationCheck() {
        val currentEmail = email.value

        if (currentEmail != null || currentEmail.isNullOrBlank()) {
            _toastEvent.value = R.string.fail_email_blank
            return
        }

        if (!isValidEmail(currentEmail)) {
            _toastEvent.value = R.string.fail_email_invalid
            return
        }

        checkEmailDuplication(currentEmail)
    }

    private fun checkEmailDuplication(email: String) = viewModelScope.launch {
        try {
            authRepository.confirmEmail(email)
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ConflictException -> R.string.fail_email_conflict
                else -> R.string.fail_exception_internal
            }
        }
    }
}