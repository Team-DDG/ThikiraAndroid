package com.dsm.restaurant.presentation.ui.main.menu.registration

import android.os.Bundle
import android.view.View
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.DialogMenuOptionAddOptionBinding
import com.dsm.restaurant.presentation.ui.base.BaseDialog
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MenuOptionAddOptionDialog : BaseDialog<DialogMenuOptionAddOptionBinding>() {
    override val layoutResId: Int = R.layout.dialog_menu_option_add_option

    private val viewModel: MenuRegistrationViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.position = arguments!!.getInt("position")
        binding.viewModel = viewModel
    }
}