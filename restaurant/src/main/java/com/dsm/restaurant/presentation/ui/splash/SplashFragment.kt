package com.dsm.restaurant.presentation.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentSplashBinding
import com.dsm.restaurant.presentation.ui.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override val layoutResId: Int = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }, 1000)
    }
}