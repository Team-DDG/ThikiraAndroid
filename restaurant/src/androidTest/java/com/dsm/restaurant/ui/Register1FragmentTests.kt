package com.dsm.restaurant.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.dsm.restaurant.BaseUiTest
import com.dsm.restaurant.R
import com.dsm.restaurant.presentation.ui.register.Register1Fragment
import com.dsm.restaurant.withTag
import org.junit.Test
import org.mockito.Mockito.verify


class Register1FragmentTests : BaseUiTest() {

    /**
     * Test can be failed when there's no image in testing device.
     */
//    @Test
//    fun imageSelectionTest() {
//        launchFragment(Register1Fragment::class.java)
//
//        onView(withText(R.string.restaurant_image)).check(matches(isDisplayed()))
//        onView(withId(R.id.pb_register1_image)).check(matches(not(isDisplayed())))
//
//        onView(withId(R.id.iv_register1_image)).perform(click())
//
//        onView(withId(R.id.rv_image)).perform(RecyclerViewActions.actionOnItemAtPosition<ImageListAdapter.ImageViewHolder>(0, click()))
//        onView(withId(R.id.tv_complete)).perform(click())
//
//        Thread.sleep(500)
//        onView(withId(R.id.tv_restaurantImage)).check(matches(not(isDisplayed())))
//        onView(withId(R.id.pb_register1_image)).check(matches(not(isDisplayed())))
//    }

    @Test
    fun addressSelectionTest() {
        launchFragment(Register1Fragment::class.java)

        onView(withId(R.id.tv_register1_address)).check(matches(withText("")))
        onView(withId(R.id.btn_register1_address)).perform(click())

        verify(mockNavController).navigate(R.id.action_register1Fragment_to_addressFragment)
    }

    @Test
    fun tagEditTextTest() {
        launchFragment(Register1Fragment::class.java)

        onView(withId(R.id.et_register1_area)).perform(replaceText("서동"), pressImeActionButton(), closeSoftKeyboard())

        onView(withId(R.id.et_register1_area)).check(matches(withTag(listOf("서동"))))
    }
}