package com.dsm.account

import android.os.Bundle
import android.view.View
import com.dsm.account.databinding.FragmentRegister4Binding
import com.dsm.account.viewModel.RegisterViewModel
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.setupNavigateEvent
import com.dsm.androidcomponent.ext.setupToastEvent
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class Register4Fragment : BaseFragment<FragmentRegister4Binding>() {
    override val layoutResId: Int = R.layout.fragment_register4

    private val viewModel: RegisterViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToastEvent(viewModel.toastEvent)
        setupNavigateEvent(viewModel.navigateEvent)

        binding.viewModel = viewModel
    }
}