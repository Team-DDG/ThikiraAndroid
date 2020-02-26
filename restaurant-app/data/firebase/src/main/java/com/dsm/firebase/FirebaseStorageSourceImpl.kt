package com.dsm.firebase

import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.FileInputStream
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FirebaseStorageSourceImpl : FirebaseStorageSource {

    private val firebaseStorage: FirebaseStorage by lazy { FirebaseStorage.getInstance() }

    override suspend fun uploadImage(
        imagePath: String,
        onSuccess: (imageUrl: String) -> Unit,
        onFailure: (Throwable) -> Unit,
        onComplete: () -> Unit
    ) = suspendCancellableCoroutine<Unit> { cout ->

        val uploadRef = firebaseStorage.reference
            .child("/restaurant")
            .child("${System.currentTimeMillis()}.png")

        uploadRef.putStream(FileInputStream(imagePath))
            .addOnSuccessListener {
                uploadRef.downloadUrl.addOnSuccessListener {
                    onSuccess(it.toString())
                    cout.resume(Unit)
                }
            }
            .addOnFailureListener {
                onFailure(it)
                cout.resumeWithException(it)
            }
            .addOnCompleteListener { onComplete() }
    }
}