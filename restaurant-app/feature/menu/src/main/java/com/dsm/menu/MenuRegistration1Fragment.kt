package com.dsm.menu

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.setupToastEvent
import com.dsm.mediapicker.MediaPicker
import com.dsm.menu.databinding.FragmentMenuRegistration1Binding
import com.dsm.menu.viewModel.MenuCategorySelectViewModel
import com.dsm.menu.viewModel.MenuRegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MenuRegistration1Fragment : BaseFragment<FragmentMenuRegistration1Binding>() {
    override val layoutResId: Int = R.layout.fragment_menu_registration1

    private val viewModel: MenuRegistrationViewModel by sharedViewModel()
    private val menuCategorySelectViewModel: MenuCategorySelectViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupMenuCategory()
        setupMediaPicker()
        setupToastEvent(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        binding.btnMenuCategory.setOnClickListener {
            findNavController().navigate(R.id.action_menuRegistration1Fragment_to_menuCategoryFragment)
        }

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_menuRegistration1Fragment_to_menuRegistration2Fragment)
        }
    }

    private fun setupMenuCategory() {
        menuCategorySelectViewModel.selectedMenuCategory.observe(this) {
            viewModel.setMenuCategory(it)
        }
    }

    private fun setupMediaPicker() {
        binding.ivMenu.setOnClickListener {
            MediaPicker.withContext(activity)
                .single()
                .theme(R.style.AppTheme)
                .toolbarBackgroundColor(R.color.colorPrimaryLight)
                .toolbarTextColor(R.color.colorMediaPickerWhite)
                .toolbarTitle(getString(R.string.select_image))
                .start { viewModel.uploadImage(it[0]) }
        }
    }
}