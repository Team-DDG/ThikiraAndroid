package com.dsm.restaurant

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.dialog_order.*


class OrderDialog : BaseDialog() {
    override val layoutResId: Int = R.layout.dialog_order

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

    override fun onResume() {
        super.onResume()
        val params: WindowManager.LayoutParams = dialog!!.window!!.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params
    }
}