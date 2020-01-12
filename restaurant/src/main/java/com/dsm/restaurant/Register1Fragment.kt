package com.dsm.restaurant

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.baseapp.BaseFragment
import kotlinx.android.synthetic.main.fragment_register1.*

class Register1Fragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_register1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tb_register1.setNavigationOnClickListener { findNavController().popBackStack() }

        btn_register1_next.setOnClickListener {
            findNavController().navigate(R.id.action_register1Fragment_to_register2Fragment)
        }
    }
}