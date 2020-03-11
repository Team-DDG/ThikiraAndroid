package com.dsm.order

import android.os.Bundle
import android.view.View
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.setupToastEvent
import com.dsm.order.databinding.FragmentOrderListBinding
import com.dsm.order.viewModel.OrderListViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.text.SimpleDateFormat
import java.util.*

class OrderListFragment : BaseFragment<FragmentOrderListBinding>() {
    override val layoutResId: Int = R.layout.fragment_order_list

    private val date: Date by lazy {
        val sdf = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
        sdf.parse(arguments?.getString("date") ?: "")
    }

    private val viewModel: OrderListViewModel by viewModel { parametersOf(date) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupToastEvent(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupRecyclerView() {
        binding.rvOrder.adapter = GroupAdapter<GroupieViewHolder>()
    }
}