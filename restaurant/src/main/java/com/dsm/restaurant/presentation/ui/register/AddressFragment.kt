package com.dsm.restaurant.presentation.ui.register

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentAddressBinding
import com.dsm.restaurant.presentation.ui.adapter.AddressListAdapter
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import com.dsm.restaurant.presentation.util.setupToast
import kotlinx.android.synthetic.main.fragment_address.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddressFragment : BaseFragment<FragmentAddressBinding>() {
    override val layoutResId: Int = R.layout.fragment_address

    private val viewModel: RegisterViewModel by sharedViewModel(from = {
        findNavController().getViewModelStoreOwner(R.id.nav_graph)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tb_location.setNavigationOnClickListener { findNavController().popBackStack() }

        setupRecyclerView()
        setupToast(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupRecyclerView() {
        val adapter = AddressListAdapter(viewModel, findNavController())
        rv_location.adapter = adapter
        rv_location.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        viewModel.addressList.observe(this, Observer {
            adapter.locationItems = it
        })
    }
}