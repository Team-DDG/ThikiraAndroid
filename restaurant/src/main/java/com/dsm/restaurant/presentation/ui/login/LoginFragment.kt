package com.dsm.restaurant.presentation.ui.login

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentLoginBinding
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    override val layoutResId: Int = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_login_register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_register1Fragment)
        }

        btn_login.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }
}