package com.example.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("banner_image")
    val bannerImage: String,
    @SerializedName("main_image")
    val mainImage: String
) {
    override fun toString(): String {
        return "[$bannerImage, $mainImage]"
    }
}
