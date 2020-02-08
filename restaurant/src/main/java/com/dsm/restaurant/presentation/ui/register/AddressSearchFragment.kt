package com.dsm.restaurant.presentation.ui.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentAddressSearchBinding
import com.dsm.restaurant.presentation.ui.adapter.AddressListAdapter
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import com.dsm.restaurant.presentation.util.setupToast
import kotlinx.android.synthetic.main.fragment_address_search.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddressSearchFragment : BaseFragment<FragmentAddressSearchBinding>() {
    override val layoutResId: Int = R.layout.fragment_address_search

    private val viewModel: RegisterViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tb_location.setNavigationOnClickListener { findNavController().popBackStack() }

        setupRecyclerView()
        setupToast(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupRecyclerView() {
        val adapter = AddressListAdapter(viewModel, findNavController())
        rv_address.adapter = adapter
        rv_address.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }
}