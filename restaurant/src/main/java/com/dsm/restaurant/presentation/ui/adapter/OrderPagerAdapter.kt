package com.dsm.restaurant.presentation.ui.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dsm.restaurant.presentation.ui.order.list.OrderListFragment
import java.text.SimpleDateFormat
import java.util.*


class OrderPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val dates = arrayListOf<String>().apply {
        val sdf = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
        (-5..0).forEach { add(sdf.format(getPreviousDate(it))) }
        notifyDataSetChanged()
    }

    private fun getPreviousDate(previous: Int): Date {
        val cal = Calendar.getInstance(Locale.KOREA)
        cal.add(Calendar.DATE, previous)
        return cal.time
    }

    fun getLastItemIndex() = dates.size - 1

    fun getDateFromPosition(position: Int) = dates[position]

    override fun getItemCount(): Int = dates.size

    override fun createFragment(position: Int): Fragment =
        OrderListFragment().apply {
            arguments = bundleOf(
                "date" to dates[position]
            )
        }
}