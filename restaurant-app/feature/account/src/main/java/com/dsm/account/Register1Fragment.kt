package com.dsm.account

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.dsm.account.databinding.FragmentRegister1Binding
import com.dsm.account.viewModel.RegisterViewModel
import com.dsm.address.viewModel.AddressSearchViewModel
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.setupToastEvent
import com.dsm.mediapicker.MediaPicker
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class Register1Fragment : BaseFragment<FragmentRegister1Binding>() {
    override val layoutResId: Int = R.layout.fragment_register1

    private val registerViewModel: RegisterViewModel by sharedViewModel()
    private val addressSearchViewModel: AddressSearchViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAddress()
        setupMediaPicker()
        setupNavigate()
        setupToastEvent(registerViewModel.toastEvent)

        binding.viewModel = registerViewModel
    }

    private fun setupAddress() {
        addressSearchViewModel.selectedAddress.observe(this) {
            registerViewModel.setAddress(it)
        }
    }

    private fun setupMediaPicker() {
        binding.ivRestaurantImage.setOnClickListener {
            MediaPicker.withContext(activity)
                .single()
                .theme(R.style.AppTheme)
                .toolbarBackgroundColor(R.color.colorPrimaryLight)
                .toolbarTextColor(R.color.colorMediaPickerWhite)
                .toolbarTitle(getString(R.string.restaurant_image))
                .start { registerViewModel.uploadImage(it[0]) }
        }
    }

    private fun setupNavigate() {
        binding.btnAddress.setOnClickListener {
            findNavController().navigate(R.id.action_register1Fragment_to_addressSearchFragment)
        }

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_register1Fragment_to_register2Fragment)
        }
    }
}