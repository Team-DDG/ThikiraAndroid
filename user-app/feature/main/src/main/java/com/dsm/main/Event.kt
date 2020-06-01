package com.dsm.main

data class Event(val bannerImageURL : String, val mainImageURL: String) {
    override fun toString(): String {
        return "[$bannerImageURL, $mainImageURL]"
    }
}
