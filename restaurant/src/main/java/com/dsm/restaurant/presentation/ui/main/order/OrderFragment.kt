package com.dsm.restaurant.presentation.ui.main.order

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentOrderBinding
import com.dsm.restaurant.presentation.ui.adapter.OrderPagerAdapter
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : BaseFragment<FragmentOrderBinding>() {
    override val layoutResId: Int = R.layout.fragment_order

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = OrderPagerAdapter(childFragmentManager, lifecycle)
        vp_order.adapter = adapter
        vp_order.setCurrentItem(adapter.getLastItemIndex(), false)

        vp_order.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                tv_order_date.text = adapter.getDateFromPosition(position)
            }
        })

        iv_order_to_previous_date.setOnClickListener {
            vp_order.setCurrentItem(vp_order.currentItem - 1, true)
        }

        iv_order_to_next_date.setOnClickListener {
            vp_order.setCurrentItem(vp_order.currentItem + 1, true)
        }
    }
}