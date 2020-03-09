package com.dsm.setting

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.androidcomponent.R
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.setting.databinding.FragmentSettingBinding
import com.dsm.setting.di.settingModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override val layoutResId: Int = com.dsm.setting.R.layout.fragment_setting

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(settingModule)

        setupNavigate()
    }

    private fun setupNavigate() {
        binding.tvChangePassword.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_passwordChangeViewModel)
        }

        binding.tvUnregister.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_unregisterDialog)
        }

        binding.tvChangeAddress.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_addressChangeFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(settingModule)
    }
}