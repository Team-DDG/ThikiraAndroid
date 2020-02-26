package com.dsm.firebase

interface FirebaseStorageSource {

    suspend fun uploadImage(
        imagePath: String,
        onSuccess: (imageUrl: String) -> Unit = {},
        onFailure: (Throwable) -> Unit = {},
        onComplete: () -> Unit = {}
    )
}