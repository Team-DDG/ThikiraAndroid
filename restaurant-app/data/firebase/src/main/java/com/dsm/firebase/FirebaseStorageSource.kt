package com.dsm.firebase

interface FirebaseStorageSource {

    suspend fun uploadImage(imagePath: String): String
}