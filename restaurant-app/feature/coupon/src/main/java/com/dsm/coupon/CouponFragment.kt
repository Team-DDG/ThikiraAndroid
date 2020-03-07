package com.dsm.coupon

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.coupon.databinding.FragmentCouponBinding

class CouponFragment : BaseFragment<FragmentCouponBinding>() {
    override val layoutResId: Int = R.layout.fragment_coupon

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
    }

    private fun setupNavigate() {
        binding.fabCoupon.setOnClickListener {
            findNavController().navigate(R.id.action_couponFragment_to_couponIssueDialog)
        }
    }
}