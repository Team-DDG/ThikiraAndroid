package com.dsm.restaurant.presentation.ui.register

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.mediapicker.MediaPicker
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentRegister1Binding
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import com.dsm.restaurant.presentation.util.setupToast
import kotlinx.android.synthetic.main.fragment_register1.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class Register1Fragment : BaseFragment<FragmentRegister1Binding>() {
    companion object {
        private const val IMAGE_CODE = 100
    }

    override val layoutResId: Int = R.layout.fragment_register1

    private val viewModel: RegisterViewModel by sharedViewModel(from = {
        findNavController().getViewModelStoreOwner(R.id.nav_graph)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iv_register1_image.setOnClickListener {
            MediaPicker.createImage(this)
                .single()
                .theme(R.style.AppTheme)
                .toolbarBackgroundColor(R.color.colorPrimaryLight)
                .toolbarTextColor(R.color.colorPickerWhite)
                .toolbarTitle(getString(R.string.select_image))
                .toolbarCompleteText(getString(R.string.select_image_complete))
                .start(IMAGE_CODE)
        }

        tb_register1.setNavigationOnClickListener { findNavController().popBackStack() }

        btn_register1_next.setOnClickListener {
            findNavController().navigate(R.id.action_register1Fragment_to_register2Fragment)
        }

        btn_register1_location.setOnClickListener {
            findNavController().navigate(R.id.action_register1Fragment_to_addressFragment)
        }

        setupToast(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == IMAGE_CODE && resultCode == RESULT_OK) {
            val imagePath = data?.getStringArrayListExtra("result")
            viewModel.uploadImage(imagePath?.get(0) ?: "")
        }
    }
}