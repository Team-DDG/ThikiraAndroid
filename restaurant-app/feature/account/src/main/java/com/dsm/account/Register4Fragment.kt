package com.dsm.account

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.dsm.account.databinding.FragmentRegister4Binding
import com.dsm.account.viewModel.RegisterViewModel
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.setupToastEvent
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class Register4Fragment : BaseFragment<FragmentRegister4Binding>() {
    override val layoutResId: Int = R.layout.fragment_register4

    private val viewModel: RegisterViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupToastEvent(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        viewModel.popToLoginEvent.observe(this) {
            findNavController().popBackStack(R.id.loginFragment, false)
        }
    }
}