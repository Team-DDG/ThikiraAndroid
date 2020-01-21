package com.dsm.thikiraandroid

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.baseapp.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: BaseFragment() {

    override val layoutResId: Int
        get() = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_login.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_mainFragment) }
        tv_register_login.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_register1Fragment)}
    }
}