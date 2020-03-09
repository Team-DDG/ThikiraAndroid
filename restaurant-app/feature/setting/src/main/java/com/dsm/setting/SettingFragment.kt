package com.dsm.setting

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.setting.databinding.FragmentSettingBinding
import com.dsm.setting.di.settingModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override val layoutResId: Int = R.layout.fragment_setting

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(settingModule)

        setupNavigate()
    }

    private fun setupNavigate() {
        binding.tvChangePassword.setOnClickListener {
            findNavController().navigate(com.dsm.androidcomponent.R.id.action_settingFragment_to_passwordChangeViewModel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(settingModule)
    }
}