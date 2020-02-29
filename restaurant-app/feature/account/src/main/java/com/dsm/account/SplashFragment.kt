package com.dsm.account

import android.os.Bundle
import android.view.View
import com.dsm.account.databinding.FragmentSplashBinding
import com.dsm.account.viewModel.SplashViewModel
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.setupNavigateEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override val layoutResId: Int = R.layout.fragment_splash

    private val viewModel: SplashViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.authToken()

        setupNavigateEvent(viewModel.navigateEvent)
    }
}