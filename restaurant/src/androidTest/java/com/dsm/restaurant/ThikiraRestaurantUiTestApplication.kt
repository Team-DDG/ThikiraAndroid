package com.dsm.restaurant

class ThikiraRestaurantUiTestApplication : ThikiraRestaurantApplication() {

    override fun getApiUrl(): String = "http://127.0.0.1:1313"

    override fun getNaverApiUrl(): String = "http://127.0.0.1:1313"
}