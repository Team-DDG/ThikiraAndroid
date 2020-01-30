package com.dsm.restaurant

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun withBackground(expectedBackground: Int): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("background color: $expectedBackground")
        }

        override fun matchesSafely(item: View?): Boolean {
            val context = item?.context!!
            val expectedDrawable = ContextCompat.getDrawable(context, expectedBackground)!!
            return expectedDrawable.constantState == item.background.constantState
        }
    }
}

fun withTextColor(expectedColor: Int): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("color: $expectedColor")
        }

        override fun matchesSafely(item: View?): Boolean {
            val context = item!!.context
            val colorId = ContextCompat.getColor(context, expectedColor)
            return when (item) {
                is TextView -> item.currentTextColor == colorId
                is Button -> item.currentTextColor == colorId
                else -> false
            }
        }

    }
}