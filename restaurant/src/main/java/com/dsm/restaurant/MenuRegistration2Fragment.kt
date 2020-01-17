package com.dsm.restaurant

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.baseapp.BaseFragment
import kotlinx.android.synthetic.main.fragment_menu_registration2.*

class MenuRegistration2Fragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_menu_registration2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tb_menuRegistration2.setNavigationOnClickListener { findNavController().popBackStack() }
    }
}