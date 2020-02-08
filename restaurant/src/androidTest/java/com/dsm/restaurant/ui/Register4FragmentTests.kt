package com.dsm.restaurant.ui

import android.net.Uri
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.dsm.restaurant.BaseUiTest
import com.dsm.restaurant.R
import com.dsm.restaurant.presentation.ui.register.Register4Fragment
import com.dsm.restaurant.withBackground
import com.dsm.restaurant.withTextColor
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.CoreMatchers.not
import org.junit.Test

class Register4FragmentTests : BaseUiTest() {

    @Test
    fun checkEmailFormBeforeDuplicationCheck() {
        launchFragment(Register4Fragment::class.java)

        onView(withId(R.id.tv_register4_duplication)).perform(click())

        onView(withId(R.id.et_register4_email)).check(matches(withText("")))
        onView(withId(R.id.et_register4_email)).check(matches(withHint(R.string.email)))
        onView(withId(R.id.snackbar_text)).check(matches(withText(R.string.fail_email_blank)))

        onView(withId(R.id.et_register4_email)).perform(typeText("hello"), closeSoftKeyboard())
        Thread.sleep(1000)
        onView(withId(R.id.tv_register4_duplication)).perform(click())

        onView(withId(R.id.snackbar_text)).check(matches(withText(R.string.fail_email_invalid)))
        onView(withId(R.id.et_register4_email)).check(matches(withText("hello")))
        onView(withId(R.id.et_register4_email)).check(matches(withHint(R.string.email)))
    }

    @Test
    fun checkEmailDuplicationSuccess() {
        setDispatcher(getDispatcher())

        launchFragment(Register4Fragment::class.java)

        onView(withId(R.id.et_register4_email)).perform(typeText("hello@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.tv_register4_duplication)).perform(click())

        onView(withId(R.id.et_register4_email)).check(matches(withText("")))
        onView(withId(R.id.et_register4_email)).check(matches(withHint("hello@gmail.com")))
    }

    @Test
    fun registerBtnClickableTest() {
        setDispatcher(getDispatcher())

        launchFragment(Register4Fragment::class.java)

        onView(withId(R.id.btn_register)).check(matches(withBackground(R.drawable.bg_button_disable)))
        onView(withId(R.id.btn_register)).check(matches(withTextColor(R.color.colorGrey)))
        onView(withId(R.id.btn_register)).check(matches(not(isClickable())))

        onView(withId(R.id.et_register4_email)).perform(typeText("hello@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.tv_register4_duplication)).perform(click())
        onView(withId(R.id.et_register4_password)).perform(typeText("password!"), pressImeActionButton())
        onView(withId(R.id.et_register4_password_re_type)).perform(typeText("password!"), closeSoftKeyboard())

        onView(withId(R.id.btn_register)).check(matches(withBackground(R.drawable.bg_button_primary_light)))
        onView(withId(R.id.btn_register)).check(matches(withTextColor(R.color.colorPrimaryLight)))
        onView(withId(R.id.btn_register)).check(matches(isClickable()))
    }

    private fun getDispatcher() = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse =
            when (Uri.parse(request.path).path) {
                "/check_email" -> MockResponse().setResponseCode(200)
                else -> MockResponse().setResponseCode(404)
            }
    }
}
