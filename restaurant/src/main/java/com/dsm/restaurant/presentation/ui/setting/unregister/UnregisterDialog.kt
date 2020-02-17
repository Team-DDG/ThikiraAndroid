package com.dsm.restaurant.presentation.ui.setting.unregister

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.DialogUnregisterBinding
import com.dsm.restaurant.presentation.ui.base.BaseDialog
import com.dsm.restaurant.presentation.util.setupToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class UnregisterDialog : BaseDialog<DialogUnregisterBinding>() {
    override val layoutResId: Int = R.layout.dialog_unregister

    private val viewModel: UnregisterViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dismissEvent.observe(this) { dismiss() }

        setupToast(viewModel.toastEvent)

        binding.viewModel = viewModel
    }
}