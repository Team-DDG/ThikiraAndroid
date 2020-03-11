package com.dsm.order.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dsm.order.OrderListFragment
import java.text.SimpleDateFormat
import java.util.*

class OrderPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    companion object {
        private const val INIT_PREVIOUS = -1
    }

    private val sdf = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
    private var lastPrevious = INIT_PREVIOUS

    private val dates = arrayListOf<String>().apply {
        (INIT_PREVIOUS..0).forEach { add(sdf.format(getPreviousDate(it))) }
        notifyDataSetChanged()
    }

    private fun getPreviousDate(previous: Int): Date {
        val cal = Calendar.getInstance(Locale.KOREA)
        cal.add(Calendar.DATE, previous)
        return cal.time
    }

    fun getLastItemIndex() = dates.size - 1

    fun getDateFromPosition(position: Int) = dates[position]

    fun addLeftPageIfNotHave(position: Int) {
        if (position == 0) {
            dates.add(0, sdf.format(getPreviousDate(--lastPrevious)))
            notifyItemInserted(0)
        }
    }

    override fun getItemCount(): Int = dates.size

    override fun createFragment(position: Int): Fragment =
        OrderListFragment().apply {
            arguments = bundleOf(
                "date" to dates[position]
            )
        }

}