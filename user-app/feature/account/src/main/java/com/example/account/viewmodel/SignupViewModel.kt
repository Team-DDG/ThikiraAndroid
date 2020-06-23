package com.example.account.viewmodel

import android.app.Activity
import androidx.lifecycle.*
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.androidcomponent.ext.isValidEmail
import com.example.account.R
import com.example.error.exception.ConflictException
import com.example.firebase.FirebaseAuthSource
import com.example.model.repository.AccountRepository
import com.example.model.repository.AuthRepository
import kotlinx.coroutines.launch

class SignupViewModel(
    private val firebaseAuthSource: FirebaseAuthSource,
    private val authRepository: AuthRepository,
    private val accountRepository: AccountRepository
) : ViewModel() {
    private var firebaseAuthCode: String = ""
    private var checkedEmail: String = ""

    val email = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordCheck = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val authCode = MutableLiveData<String>()

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _popToLoginEvent = SingleLiveEvent<Unit>()
    val popToLoginEvent: LiveData<Unit> = _popToLoginEvent

    private val _isNextEnable = MutableLiveData(false)
    val isNextEnable: LiveData<Boolean> = _isNextEnable

    private val _isEmailValid = MutableLiveData(false)
    val isEmailValid: LiveData<Boolean> = _isEmailValid

    val isStartVerifyEnable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(phone) { value = !phone.value.isNullOrBlank() }
        addSource(isNextEnable) { value = !isNextEnable.value!! }
    }

    val isVerifyEnable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(authCode) { value = !authCode.value?.isBlank()!! }
        addSource(isNextEnable) { value = !isNextEnable.value!! }
    }

    val isEmailVerified: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        listOf(isEmailValid, email).forEach {
            addSource(it) { value =  isEmailValid.value!! && (email.value == checkedEmail) }
        }
    }

    val isPasswordChecked: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(passwordCheck) { value = password.value == passwordCheck.value }
    }

    val isSignupEnable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        listOf(email, password, passwordCheck, nickname).forEach {
            addSource(it) {
                value = !(email.value.isNullOrBlank() || password.value.isNullOrBlank() ||
                        passwordCheck.value.isNullOrBlank() || nickname.value.isNullOrBlank()) && isEmailValid.value!!
            }
        }
    }

    fun onClickStartVerify(activity: Activity) {
        var currPhoneNum = phone.value!!
        currPhoneNum = "+82" + currPhoneNum.removeRange(0, 0)
        //only for korean phone number(international phone number)

        firebaseAuthSource.userAuthWithPhone(
            currPhoneNum,
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
        if (firebaseAuthCode == authCode.value) {
            _toastEvent.value = R.string.success_verify_phone
            _isNextEnable.value = true
        } else {
            _toastEvent.value = R.string.fail_verify_phone
        }
    }

    fun onClickDuplicationCheck() {
        val currentEmail = email.value

        if (currentEmail.isNullOrBlank()) {
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
            _toastEvent.value = R.string.success_email_duplication_check

            checkedEmail = email
            _isEmailValid.value = true
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ConflictException -> R.string.fail_email_conflict
                else -> R.string.fail_exception_internal
            }
        }
    }

    fun onClickSignup() = viewModelScope.launch {
        if(email.value != checkedEmail){
            _toastEvent.value = R.string.fail_email_changed
        } else {
            try {
                accountRepository.register(
                    hashMapOf(
                        "email" to email.value,
                        "nickname" to nickname.value,
                        "password" to password.value,
                        "phone" to phone.value
                    )
                )

                _toastEvent.value = R.string.success_signin_user
                _popToLoginEvent.call()
            } catch (e: Exception) {
                _toastEvent.value = when (e) {
                    is ConflictException -> R.string.fail_nickname_conflict
                    else -> R.string.fail_exception_internal
                }
            }
        }
    }
}