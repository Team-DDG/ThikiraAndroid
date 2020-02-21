package com.dsm.restaurant.data.firebase

import com.google.firebase.storage.FirebaseStorage
import java.io.FileInputStream

interface FirebaseStorageSource {

    fun uploadImage(
        imagePath: String,
        onSuccess: (imagePath: String) -> Unit = {},
        onFailure: (exception: Exception) -> Unit = {},
        onComplete: () -> Unit = {}
    )
}

class FirebaseStorageSourceImpl : FirebaseStorageSource {

    private val firebaseStorage: FirebaseStorage by lazy { FirebaseStorage.getInstance() }

    override fun uploadImage(
        imagePath: String,
        onSuccess: (imagePath: String) -> Unit,
        onFailure: (exception: Exception) -> Unit,
        onComplete: () -> Unit
    ) {
        val uploadRef = firebaseStorage.reference
            .child("/restaurant")
            .child(System.currentTimeMillis().toString() + ".png")

        uploadRef.putStream(FileInputStream(imagePath))
            .addOnSuccessListener {
                uploadRef.downloadUrl.addOnSuccessListener { onSuccess(it.toString()) }
            }
            .addOnFailureListener { onFailure(it) }
            .addOnCompleteListener { onComplete() }
    }

}