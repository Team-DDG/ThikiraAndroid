package com.dsm.restaurant.presentation.ui.order.list

import android.os.Bundle
import android.view.View
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentOrderListBinding
import com.dsm.restaurant.presentation.ui.adapter.OrderListAdapter
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import com.dsm.restaurant.trashModel.OrderModel
import kotlinx.android.synthetic.main.fragment_order_list.*

class OrderListFragment : BaseFragment<FragmentOrderListBinding>() {
    override val layoutResId: Int = R.layout.fragment_order_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = OrderListAdapter()
        rv_order_list.adapter = adapter

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