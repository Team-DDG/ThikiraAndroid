package com.dsm.restaurant.presentation.ui.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentRegister3Binding
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_register3.*

class Register3Fragment : BaseFragment<FragmentRegister3Binding>() {
    override val layoutResId: Int = R.layout.fragment_register3

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tb_register3.setNavigationOnClickListener { findNavController().popBackStack() }

        btn_register3_next.setOnClickListener {
            findNavController().navigate(R.id.action_register3Fragment_to_register4Fragment)
        }
    }
}