package com.dsm.address

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dsm.address.databinding.FragmentAddressSearchBinding
import com.dsm.address.viewModel.AddressSearchViewModel
import com.dsm.androidcomponent.base.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddressSearchFragment : BaseFragment<FragmentAddressSearchBinding>() {
    override val layoutResId: Int = R.layout.fragment_address_search

    private val viewModel: AddressSearchViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupRecyclerView()

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        viewModel.popEvent.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })
    }

    private fun setupRecyclerView() {
        binding.rvAddress.adapter = GroupAdapter<GroupieViewHolder>()
    }
}