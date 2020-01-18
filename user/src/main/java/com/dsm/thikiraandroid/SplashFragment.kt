package com.dsm.thikiraandroid

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.baseapp.BaseFragment

class SplashFragment : BaseFragment() {

    override val layoutResId: Int
        get() = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({ findNavController().navigate(R.id.action_splashFragment_to_loginFragment) }, 1000)
    }
}