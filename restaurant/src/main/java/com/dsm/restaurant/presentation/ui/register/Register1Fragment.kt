package com.dsm.restaurant.presentation.ui.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.baseapp.BaseFragment
import com.dsm.restaurant.R
import kotlinx.android.synthetic.main.fragment_register1.*

class Register1Fragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_register1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tb_register1.setNavigationOnClickListener { findNavController().popBackStack() }

        btn_register1_next.setOnClickListener {
            findNavController().navigate(R.id.action_register1Fragment_to_register2Fragment)
        }

        btn_register1_location.setOnClickListener {
            findNavController().navigate(R.id.action_register1Fragment_to_locationFragment)
        }
    }
}