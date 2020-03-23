package com.dsm.account

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.dsm.account.databinding.FragmentLoginBinding
import com.dsm.account.viewModel.LoginViewModel
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.hideKeyboard
import com.dsm.androidcomponent.ext.setupNavigateEvent
import com.dsm.androidcomponent.ext.setupToastEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val layoutResId: Int = R.layout.fragment_login

    private val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupToastEvent(viewModel.toastEvent)

        viewModel.hideKeyEvent.observe(this) { hideKeyboard() }

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        setupNavigateEvent(viewModel.navigateEvent)

        binding.tvToRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_register1Fragment)
        }
    }
}