package com.dsm.order

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.order.adapter.OrderPagerAdapter
import com.dsm.order.databinding.FragmentOrderBinding

class OrderFragment : BaseFragment<FragmentOrderBinding>() {
    override val layoutResId: Int = R.layout.fragment_order

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = OrderPagerAdapter(childFragmentManager, lifecycle)
        binding.vpOrder.adapter = adapter
        binding.vpOrder.setCurrentItem(adapter.getLastItemIndex(), false)
        binding.vpOrder.offscreenPageLimit = 10

        binding.vpOrder.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                binding.tvDate.text = adapter.getDateFromPosition(position)
                adapter.addLeftPageIfNotHave(position)
            }
        })

        binding.ivToPreviousDate.setOnClickListener {
            binding.vpOrder.setCurrentItem(binding.vpOrder.currentItem - 1, true)
        }

        binding.ivToNextDate.setOnClickListener {
            binding.vpOrder.setCurrentItem(binding.vpOrder.currentItem + 1, true)
        }
    }
}