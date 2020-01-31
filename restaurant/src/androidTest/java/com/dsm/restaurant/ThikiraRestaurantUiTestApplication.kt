package com.dsm.restaurant

class ThikiraRestaurantUiTestApplication : ThikiraRestaurantApplication() {

    override fun getApiUrl(): String = "http://127.0.0.1:8080"

    override fun getNaverApiUrl(): String = "http://127.0.0.1:8080"
}