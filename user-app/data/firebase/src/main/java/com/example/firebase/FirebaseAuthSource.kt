package com.example.firebase

import android.app.Activity
import com.google.firebase.auth.PhoneAuthProvider

interface FirebaseAuthSource {
    fun userAuthWithPhone(
        phoneNumber: String,
        activity: Activity,
        onVerificationCompleted: (code: String) -> Unit,
        onVerificationFailed: () -> Unit
    )
}