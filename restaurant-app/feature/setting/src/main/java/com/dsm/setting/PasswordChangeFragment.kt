package com.dsm.setting

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.setupToastEvent
import com.dsm.setting.databinding.FragmentPasswordChangeBinding
import com.dsm.setting.viewModel.PasswordChangeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PasswordChangeFragment : BaseFragment<FragmentPasswordChangeBinding>() {
    override val layoutResId: Int = R.layout.fragment_password_change

    private val viewModel: PasswordChangeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupToastEvent(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        viewModel.popEvent.observe(this) { findNavController().popBackStack() }
    }
}