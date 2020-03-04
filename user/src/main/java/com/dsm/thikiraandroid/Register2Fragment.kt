package com.dsm.thikiraandroid

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_register2.*

class Register2Fragment : BaseFragment() {

    override val layoutResId: Int
        get() = R.layout.fragment_register2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_register.setOnClickListener { findNavController().popBackStack(R.id.loginFragment, true) }
    }
}