package com.dsm.restaurant.presentation.ui.main.setting

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.baseapp.BaseFragment
import com.dsm.restaurant.R
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_setting

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tb_setting.setNavigationOnClickListener { activity?.finish() }

        tv_setting_changePwd.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_changePwdDialog)
        }
    }
}