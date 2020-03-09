package com.dsm.setting

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.dsm.address.viewModel.AddressSelectViewModel
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.setupToastEvent
import com.dsm.setting.databinding.FragmentAddressChangeBinding
import com.dsm.setting.viewModel.AddressChangeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddressChangeFragment : BaseFragment<FragmentAddressChangeBinding>() {
    override val layoutResId: Int = R.layout.fragment_address_change

    private val viewModel: AddressChangeViewModel by viewModel()

    private val addressSelectViewModel: AddressSelectViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupAddressSelect()
        setupToastEvent(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        binding.btnSearchNewAddress.setOnClickListener {
            findNavController().navigate(com.dsm.androidcomponent.R.id.action_addressChangeFragment_to_addressSearchFragment)
        }

        viewModel.popEvent.observe(this) {
            findNavController().popBackStack(com.dsm.androidcomponent.R.id.settingFragment, false)
        }
    }

    private fun setupAddressSelect() {
        addressSelectViewModel.selectedAddress.observe(this) {
            viewModel.setNewAddress(it)
        }
    }
}