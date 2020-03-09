package com.dsm.setting

import android.os.Bundle
import android.view.View
import com.dsm.androidcomponent.base.BaseDialog
import com.dsm.androidcomponent.ext.setupNavigateEvent
import com.dsm.androidcomponent.ext.setupToastEvent
import com.dsm.setting.databinding.DialogUnregisterBinding
import com.dsm.setting.viewModel.UnregisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UnregisterDialog : BaseDialog<DialogUnregisterBinding>() {
    override val layoutResId: Int = R.layout.dialog_unregister

    private val viewModel: UnregisterViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToastEvent(viewModel.toastEvent)
        setupNavigateEvent(viewModel.navigateEvent)

        binding.viewModel = viewModel
    }
}