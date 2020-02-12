package com.dsm.restaurant.ui

import android.net.Uri
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import com.dsm.restaurant.BaseUiTest
import com.dsm.restaurant.R
import com.dsm.restaurant.presentation.ui.main.menu.list.MenuListFragment
import com.dsm.restaurant.recyclerViewItemCount
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.CoreMatchers.containsString
import org.junit.Test

class MenuListFragmentTests : BaseUiTest() {

    @Test
    fun getMenuCategoryAndMenuSuccess() {
        setDispatcher(getDispatcher())
        launchFragment(MenuListFragment::class.java)

        onView(withId(R.id.spn_menu_list)).check(matches(withSpinnerText(containsString("치킨"))))
        onView(withId(R.id.rv_menu_list)).check(recyclerViewItemCount(1))
    }

    @Test
    fun getMenuCategoryFailed() {
        setDispatcher(getErrorDispatcher(403))
        launchFragment(MenuListFragment::class.java)

        onView(withId(R.id.spn_menu_list)).check(matches(withSpinnerText(containsString(""))))
        onView(withId(R.id.rv_menu_list)).check(recyclerViewItemCount(0))
    }

    private fun getDispatcher() = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse =
            when (Uri.parse(request.path).path) {
                "/menu/category" -> MockResponse().setResponseCode(200).setBody(parseJsonFile("menu_category.json"))
                "/menu" -> MockResponse().setResponseCode(200).setBody(parseJsonFile("menu.json"))
                else -> MockResponse().setResponseCode(404)
            }

    }
}