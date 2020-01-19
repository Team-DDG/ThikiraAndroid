package com.dsm.restaurant.presentation.ui.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.baseapp.BaseFragment
import com.dsm.restaurant.R
import kotlinx.android.synthetic.main.fragment_register3.*

class Register3Fragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_register3

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tb_register3.setNavigationOnClickListener { findNavController().popBackStack() }

        btn_register3_next.setOnClickListener {
            findNavController().navigate(R.id.action_register3Fragment_to_register4Fragment)
        }
    }
}