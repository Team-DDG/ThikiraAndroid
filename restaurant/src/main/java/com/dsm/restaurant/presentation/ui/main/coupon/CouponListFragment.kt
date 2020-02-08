package com.dsm.restaurant.presentation.ui.main.coupon

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentCouponListBinding
import com.dsm.restaurant.presentation.ui.adapter.CouponListAdapter
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import com.dsm.restaurant.trashModel.CouponModel
import kotlinx.android.synthetic.main.fragment_coupon_list.*

class CouponListFragment : BaseFragment<FragmentCouponListBinding>() {
    override val layoutResId: Int = R.layout.fragment_coupon_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CouponListAdapter()
        rv_coupon_list.adapter = adapter
        adapter.submitList(
            (0..10).map {
                CouponModel(it, "발행 중인 쿠폰", "2020.01.01", "1,000")
            }
        )

        rv_coupon_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0 && !fab_coupon_list.isShown) {
                    fab_coupon_list.show()
                } else if (dy > 0 && fab_coupon_list.isShown) {
                    fab_coupon_list.hide()
                }
            }
        })

        fab_coupon_list.setOnClickListener {
            findNavController().navigate(R.id.action_couponListFragment_to_couponIssueDialog)
        }
    }
}