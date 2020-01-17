package com.dsm.restaurant

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.baseapp.BaseFragment
import kotlinx.android.synthetic.main.fragment_menu_registration1.*

class MenuRegistration1Fragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_menu_registration1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tb_menuRegistration1.setNavigationOnClickListener { activity?.finish() }

        btn_menuRegistration_next.setOnClickListener {
            findNavController().navigate(R.id.action_menuRegistration1Fragment_to_menuRegistration2Fragment)
        }
    }
}