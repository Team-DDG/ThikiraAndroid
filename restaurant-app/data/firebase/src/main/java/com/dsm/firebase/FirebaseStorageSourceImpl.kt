package com.dsm.firebase

import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.FileInputStream
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FirebaseStorageSourceImpl : FirebaseStorageSource {

    private val firebaseStorage: FirebaseStorage by lazy { FirebaseStorage.getInstance() }

    override suspend fun uploadImage(imagePath: String) = suspendCancellableCoroutine<String> { cout ->

        val uploadRef = firebaseStorage.reference
            .child("/restaurant")
            .child("${System.currentTimeMillis()}.png")

        uploadRef.putStream(FileInputStream(imagePath))
            .addOnSuccessListener {
                uploadRef.downloadUrl.addOnSuccessListener {
                    cout.resume(it.toString())
                }
            }
            .addOnFailureListener { cout.resumeWithException(it) }
    }
}