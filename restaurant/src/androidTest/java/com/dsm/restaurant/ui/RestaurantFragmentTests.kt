package com.dsm.restaurant.ui

import android.net.Uri
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.dsm.restaurant.BaseUiTest
import com.dsm.restaurant.R
import com.dsm.restaurant.presentation.ui.restaurant.RestaurantFragment
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Test

class RestaurantFragmentTests : BaseUiTest() {

    @Test
    fun getRestaurantInfoSuccessTest() {
        setDispatcher(getDispatcher())

        launchFragment(RestaurantFragment::class.java)

        Thread.sleep(1000)

        onView(withId(R.id.tv_restaurant_time)).check(matches(withText("10:30~11:30")))
        onView(withId(R.id.tv_restaurant_phone)).check(matches(withText("phone")))
        onView(withId(R.id.tv_restaurant_email)).check(matches(withText("Email")))
        onView(withId(R.id.tv_restaurant_address)).check(matches(withText("add_parcel")))
        onView(withId(R.id.tv_restaurant_area)).check(matches(withText("area1,area2,area3")))
        onView(withId(R.id.tv_restaurant_category)).check(matches(withText("category")))
        onView(withId(R.id.tv_restaurant_min_price)).check(matches(withText("10000")))
        onView(withId(R.id.tv_restaurant_day_off)).check(matches(withText("day_off")))
        onView(withId(R.id.tv_restaurant_description)).check(matches(withText("안녕하세요 김도훈입니다")))
    }

    private fun getDispatcher() = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse =
            when (Uri.parse(request.path).path) {
                "/" -> MockResponse().setResponseCode(200).setBody(parseJsonFile("restaurant.json"))
                else -> MockResponse().setResponseCode(404)
            }
    }
}