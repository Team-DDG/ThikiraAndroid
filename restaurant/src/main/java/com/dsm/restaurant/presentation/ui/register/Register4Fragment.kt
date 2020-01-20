package com.dsm.restaurant.presentation.ui.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentRegister4Binding
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_register4.*

class Register4Fragment : BaseFragment<FragmentRegister4Binding>() {
    override val layoutResId: Int = R.layout.fragment_register4

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tb_register4.setNavigationOnClickListener { findNavController().popBackStack() }

        btn_register.setOnClickListener {
            findNavController().popBackStack(R.id.loginFragment, true)
        }
    }
}