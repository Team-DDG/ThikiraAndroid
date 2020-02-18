package com.dsm.restaurant.presentation.ui.address

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentAddressSearchBinding
import com.dsm.restaurant.presentation.ui.adapter.AddressListAdapter
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import com.dsm.restaurant.presentation.util.setupToast
import kotlinx.android.synthetic.main.fragment_address_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddressSearchFragment : BaseFragment<FragmentAddressSearchBinding>() {
    override val layoutResId: Int = R.layout.fragment_address_search

    private val viewModel: AddressSearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupRecyclerView()
        setupToast(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        tb_address_search.setNavigationOnClickListener { findNavController().popBackStack() }

        viewModel.popBackStackEvent.observe(this) { findNavController().popBackStack() }
    }

    private fun setupRecyclerView() {
        val adapter = AddressListAdapter(viewModel)
        rv_address_search.adapter = adapter
        rv_address_search.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }
}