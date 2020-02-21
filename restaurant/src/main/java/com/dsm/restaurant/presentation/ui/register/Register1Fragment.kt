package com.dsm.restaurant.presentation.ui.register

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.mediapicker.MediaPicker
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentRegister1Binding
import com.dsm.restaurant.presentation.model.AddressModel
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import com.dsm.restaurant.presentation.util.BusProvider
import com.dsm.restaurant.presentation.util.setupToast
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.fragment_register1.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class Register1Fragment : BaseFragment<FragmentRegister1Binding>() {
    companion object {
        private const val IMAGE_CODE = 100
    }

    override val layoutResId: Int = R.layout.fragment_register1

    private val viewModel: RegisterViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupImagePicker()
        setupToast(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        tb_register1.setNavigationOnClickListener { findNavController().popBackStack() }

        btn_register1_next.setOnClickListener {
            findNavController().navigate(R.id.action_register1Fragment_to_register2Fragment)
        }

        btn_register1_select_address.setOnClickListener {
            findNavController().navigate(R.id.action_register1Fragment_to_addressSearchFragment)
        }
    }

    private fun setupImagePicker() =
        iv_register1_restaurant_image.setOnClickListener {
            MediaPicker.createImage(this)
                .single()
                .theme(R.style.AppTheme)
                .toolbarBackgroundColor(R.color.colorPrimaryLight)
                .toolbarTextColor(R.color.colorPickerWhite)
                .toolbarTitle(getString(R.string.select_image))
                .start(IMAGE_CODE)
        }

    @Subscribe
    fun subscribeAddress(address: AddressModel) {
        viewModel.setAddress(
            address = address.address,
            roadAddress = address.roadAddress
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == IMAGE_CODE && resultCode == RESULT_OK) {
            val imagePath = data?.getStringArrayListExtra("result")
            viewModel.uploadImage(imagePath?.get(0) ?: "")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.getInstance().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        BusProvider.getInstance().unregister(this)
    }
}