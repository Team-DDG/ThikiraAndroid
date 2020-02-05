package com.dsm.restaurant.presentation.ui.main.setting.changePwd

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.DialogChangePwdBinding
import com.dsm.restaurant.presentation.ui.base.BaseDialog
import com.dsm.restaurant.presentation.util.setupToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePwdDialog : BaseDialog<DialogChangePwdBinding>() {
    override val layoutResId: Int = R.layout.dialog_change_pwd

    private val viewModel: ChangePwdViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dismissEvent.observe(this, Observer { dismiss() })

        setupToast(viewModel.toastEvent)

        binding.viewModel = viewModel
    }
}