package com.dsm.restaurant.presentation.ui.main.coupon

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dsm.baseapp.BaseFragment
import com.dsm.restaurant.R
import com.dsm.restaurant.presentation.ui.adapter.CouponListAdapter
import com.dsm.restaurant.trashModel.CouponModel
import kotlinx.android.synthetic.main.fragment_coupon.*

class CouponFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_coupon

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CouponListAdapter()
        rv_coupon.adapter = adapter
        adapter.submitList(
            (0..10).map {
                CouponModel(it, "발행 중인 쿠폰", "2020.01.01", "1,000")
            }
        )

        rv_coupon.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0 && !fab_coupon.isShown) {
                    fab_coupon.show()
                } else if (dy > 0 && fab_coupon.isShown) {
                    fab_coupon.hide()
                }
            }
        })

        fab_coupon.setOnClickListener {
            findNavController().navigate(R.id.action_couponFragment_to_couponDialog)
        }
    }
}