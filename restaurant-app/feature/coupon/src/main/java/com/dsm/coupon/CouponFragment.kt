package com.dsm.coupon

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.setupToastEvent
import com.dsm.coupon.databinding.FragmentCouponBinding
import com.dsm.coupon.viewModel.CouponViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class CouponFragment : BaseFragment<FragmentCouponBinding>() {
    override val layoutResId: Int = R.layout.fragment_coupon

    private val viewModel: CouponViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupRecyclerView()
        setupToastEvent(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        binding.fabCoupon.setOnClickListener {
            findNavController().navigate(R.id.action_couponFragment_to_couponIssueDialog)
        }
    }

    private fun setupRecyclerView() {
        binding.rvCoupon.adapter = GroupAdapter<GroupieViewHolder>()
    }
}