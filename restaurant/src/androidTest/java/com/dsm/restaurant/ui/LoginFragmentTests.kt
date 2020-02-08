package com.dsm.restaurant.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dsm.restaurant.BaseUiTest
import com.dsm.restaurant.R
import com.dsm.restaurant.presentation.ui.login.LoginFragment
import com.dsm.restaurant.withBackground
import com.dsm.restaurant.withTextColor
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoInteractions


class LoginFragmentTests : BaseUiTest() {

    @Test
    fun loginButtonClickableTest() {
        setDispatcher(getDispatcher())

        launchFragment(LoginFragment::class.java)

        onView(withId(R.id.btn_login)).check(matches(withBackground(R.drawable.bg_button_disable)))
        onView(withId(R.id.btn_login)).check(matches(withTextColor(R.color.colorGrey)))
        onView(withId(R.id.btn_login)).check(matches(not(isClickable())))

        onView(withId(R.id.et_login_email)).perform(typeText("hello@naver.com"), pressImeActionButton())
        onView(withId(R.id.et_login_password)).perform(typeText("password!"), closeSoftKeyboard())

        onView(withId(R.id.btn_login)).check(matches(withBackground(R.drawable.bg_button_primary_light)))
        onView(withId(R.id.btn_login)).check(matches(withTextColor(R.color.colorPrimaryLight)))
        onView(withId(R.id.btn_login)).check(matches(isClickable()))
    }

    @Test
    fun loginSuccessTest() {
        setDispatcher(getDispatcher())

        launchFragment(LoginFragment::class.java)

        onView(withId(R.id.et_login_email)).perform(typeText("hello@naver.com"), pressImeActionButton())
        onView(withId(R.id.et_login_password)).perform(typeText("password!"), closeSoftKeyboard())

        onView(withId(R.id.btn_login)).perform(click())

        verify(mockNavController).navigate(R.id.action_loginFragment_to_mainFragment)
    }

    @Test
    fun loginNotFoundTest() {
        setDispatcher(getErrorDispatcher(404))

        launchFragment(LoginFragment::class.java)

        onView(withId(R.id.et_login_email)).perform(typeText("hello@naver.com"), pressImeActionButton())
        onView(withId(R.id.et_login_password)).perform(typeText("password!"), closeSoftKeyboard())

        onView(withId(R.id.btn_login)).perform(click())

        verifyNoInteractions(mockNavController)
    }

    private fun getDispatcher() = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse =
            when (request.path) {
                "/auth/login" -> MockResponse().setResponseCode(200).setBody(parseJsonFile("login.json"))
                else -> MockResponse().setResponseCode(404)
            }
    }
}