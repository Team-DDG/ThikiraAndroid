package com.dsm.restaurant.data.firebase

interface FirebaseSource {

    interface UploadListener {
        fun onSuccess(imageUrl: String)

        fun onFailure(exception: Exception)
    }

    fun uploadImage(imagePath: String, uploadListener: UploadListener)
}