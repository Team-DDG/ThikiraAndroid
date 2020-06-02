package com.example.model

data class Event(val bannerImageURL : String, val mainImageURL: String) {
    override fun toString(): String {
        return "[$bannerImageURL, $mainImageURL]"
    }
}
