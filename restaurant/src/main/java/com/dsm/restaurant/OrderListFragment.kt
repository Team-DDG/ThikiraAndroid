package com.dsm.restaurant

import android.os.Bundle
import android.view.View
import com.dsm.baseapp.BaseFragment
import kotlinx.android.synthetic.main.fragment_order_list.*

class OrderListFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_order_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = OrderListAdapter()
        rv_order.adapter = adapter

        adapter.submitList((0..5).map {
            OrderModel(
                orderId = it,
                price = "10,000",
                address = "A동 A아파트 101동 101호",
                method = "오프라인 결제",
                name = "김도훈",
                orderTime = "16:02",
                status = "배달 중"
            )
        })
    }
}