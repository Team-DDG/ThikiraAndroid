package com.dsm.restaurant.presentation.ui.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentSplashBinding
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override val layoutResId: Int = R.layout.fragment_splash

    private val viewModel: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
    }

    private fun setupNavigate() {
        viewModel.navigateLoginEvent.observe(this) {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }

        viewModel.navigateMainEvent.observe(this) {
            findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        }
    }
}