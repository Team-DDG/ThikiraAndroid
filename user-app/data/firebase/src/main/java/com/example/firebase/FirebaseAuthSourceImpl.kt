package com.example.firebase

import android.app.Activity
import android.util.Log
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class FirebaseAuthSourceImpl : FirebaseAuthSource {

    private val phoneAuthProvider: PhoneAuthProvider by lazy { PhoneAuthProvider.getInstance() }

    override fun userAuthWithPhone(
        phoneNumber: String,
        activity: Activity,
        onVerificationCompleted: (code: String) -> Unit,
        onVerificationFailed: () -> Unit
    ) {
        Log.d("firebase_log", phoneNumber)
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,
            120,
            TimeUnit.SECONDS,
            activity,
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    val code = p0.smsCode
                    if (code != null) {
                        onVerificationCompleted(code)
                    }
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Log.d("firebase_log", p0.message)
                    onVerificationFailed()
                }
            }
        )
    }
}