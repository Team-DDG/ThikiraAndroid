package com.dsm.restaurant.presentation.ui.setting

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentSettingBinding
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override val layoutResId: Int = R.layout.fragment_setting

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tb_setting.setNavigationOnClickListener { activity?.finish() }

        tv_setting_change_password.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_passwordChangeDialog)
        }

        tv_setting_unregister.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_unregisterDialog)
        }
    }
}