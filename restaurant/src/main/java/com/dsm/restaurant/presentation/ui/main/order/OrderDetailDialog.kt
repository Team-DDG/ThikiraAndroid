package com.dsm.restaurant.presentation.ui.main.order

import android.os.Bundle
import android.view.View
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.DialogOrderDetailBinding
import com.dsm.restaurant.presentation.ui.adapter.OrderedListAdapter
import com.dsm.restaurant.presentation.ui.base.BaseDialog
import com.dsm.restaurant.trashModel.OrderedModel
import kotlinx.android.synthetic.main.dialog_order_detail.*


class OrderDetailDialog : BaseDialog<DialogOrderDetailBinding>() {
    override val layoutResId: Int = R.layout.dialog_order_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = OrderedListAdapter()
        rv_ordered.adapter = adapter

        adapter.orderedItems = listOf(
            OrderedModel.Ordered("도훈이의 총각김치", "5", "5,000"),
            OrderedModel.Option("고추가루 추가"),
            OrderedModel.Ordered("도훈이의 총각김치", "5", "5,000"),
            OrderedModel.Option("고추가루 추가"),
            OrderedModel.Ordered("도훈이의 총각김치", "5", "5,000"),
            OrderedModel.Option("고추가루 추가")
        )
    }
}