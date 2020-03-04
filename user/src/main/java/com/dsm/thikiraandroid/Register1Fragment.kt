package com.dsm.thikiraandroid

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_register1.*

class Register1Fragment: BaseFragment() {

    override val layoutResId: Int
        get() = R.layout.fragment_register1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_next_register1.setOnClickListener { findNavController().navigate(R.id.action_register1Fragment_to_register2Fragment) }
    }
}