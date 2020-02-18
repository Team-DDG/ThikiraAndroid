package com.dsm.restaurant.presentation.ui.setting.changePwd

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.DialogPasswordChangeBinding
import com.dsm.restaurant.presentation.ui.base.BaseDialog
import com.dsm.restaurant.presentation.util.setupToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class PasswordChangeDialog : BaseDialog<DialogPasswordChangeBinding>() {
    override val layoutResId: Int = R.layout.dialog_password_change

    private val viewModel: PasswordChangeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dismissEvent.observe(this) { dismiss() }
        setupToast(viewModel.toastEvent)

        binding.viewModel = viewModel
    }
}