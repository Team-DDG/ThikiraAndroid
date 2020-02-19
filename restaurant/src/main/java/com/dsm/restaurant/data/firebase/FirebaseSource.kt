package com.dsm.restaurant.data.firebase

interface FirebaseSource {

    fun uploadImage(
        imagePath: String,
        onSuccess: (imagePath: String) -> Unit = {},
        onFailure: (exception: Exception) -> Unit = {},
        onComplete: () -> Unit = {}
    )
}