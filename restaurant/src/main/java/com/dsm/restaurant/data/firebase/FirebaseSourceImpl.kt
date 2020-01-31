package com.dsm.restaurant.data.firebase

import com.google.firebase.storage.FirebaseStorage
import java.io.FileInputStream

class FirebaseSourceImpl : FirebaseSource {

    private val firebaseStorage: FirebaseStorage by lazy { FirebaseStorage.getInstance() }

    override fun uploadImage(imagePath: String, uploadListener: FirebaseSource.UploadListener) {
        val uploadRef = firebaseStorage.reference
            .child("/restaurant")
            .child(System.currentTimeMillis().toString() + ".png")

        uploadRef.putStream(FileInputStream(imagePath))
            .addOnSuccessListener {
                uploadRef.downloadUrl.addOnSuccessListener { uploadListener.onSuccess(it.toString()) }
            }
            .addOnFailureListener { uploadListener.onFailure(it) }
            .addOnCompleteListener { uploadListener.onComplete() }
    }

}