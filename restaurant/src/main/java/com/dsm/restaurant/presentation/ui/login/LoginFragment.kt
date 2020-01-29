package com.dsm.restaurant.presentation.ui.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentLoginBinding
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import com.dsm.restaurant.presentation.util.hideKeyborad
import com.dsm.restaurant.presentation.util.setupToast
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val layoutResId: Int = R.layout.fragment_login

    private val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupKeyboard()
        setupToast(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        tv_login_register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_nav_register)
        }

        viewModel.navigateMainEvent.observe(this, Observer {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        })
    }

    private fun setupKeyboard() {
        viewModel.hideKeyboardEvent.observe(this, Observer { hideKeyborad() })
    }
}