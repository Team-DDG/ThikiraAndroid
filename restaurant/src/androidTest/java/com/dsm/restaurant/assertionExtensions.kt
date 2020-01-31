package com.dsm.restaurant

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.core.Is.`is`

fun recyclerViewItemCount(expectedItemCount: Int) = ViewAssertion { view, noViewFoundException ->
    noViewFoundException?.let { throw it }

    assertThat((view as RecyclerView).adapter?.itemCount, `is`(expectedItemCount))
}