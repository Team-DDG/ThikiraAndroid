package com.dsm.restaurant.presentation.ui.register

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentRegister4Binding
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import com.dsm.restaurant.presentation.util.setupSnackbar
import com.dsm.restaurant.presentation.util.setupToast
import kotlinx.android.synthetic.main.fragment_register4.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class Register4Fragment : BaseFragment<FragmentRegister4Binding>() {
    override val layoutResId: Int = R.layout.fragment_register4

    private val viewModel: RegisterViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupMessage()

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        tb_register4.setNavigationOnClickListener { findNavController().popBackStack() }

        btn_register.setOnClickListener {
            findNavController().popBackStack(R.id.loginFragment, true)
        }

        viewModel.popToLoginEvent.observe(this, Observer {
            findNavController().navigate(R.id.action_register4Fragment_to_loginFragment)
        })
    }

    private fun setupMessage() {
        setupSnackbar(viewModel.snackbarEvent)
        setupToast(viewModel.toastEvent)
    }
}