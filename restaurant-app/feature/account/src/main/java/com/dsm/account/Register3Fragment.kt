package com.dsm.account

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.account.databinding.FragmentRegister3Binding
import com.dsm.account.viewModel.RegisterViewModel
import com.dsm.androidcomponent.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class Register3Fragment : BaseFragment<FragmentRegister3Binding>() {
    override val layoutResId: Int = R.layout.fragment_register3

    private val viewModel: RegisterViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_register3Fragment_to_register4Fragment)
        }
    }
}