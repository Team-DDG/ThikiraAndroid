package com.example.firebase

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class FirebaseAuthSourceImpl : FirebaseAuthSource {

    companion object {
        const val VERIFY_PHONE_TIME_OUT_DURATION: Long = 120
    }

    override fun userAuthWithPhone(
        phoneNumber: String,
        activity: Activity,
        onVerificationCompleted: (code: String) -> Unit,
        onVerificationFailed: () -> Unit
    ) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,
            VERIFY_PHONE_TIME_OUT_DURATION,
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
                    onVerificationFailed()
                }
            }
        )
    }
}