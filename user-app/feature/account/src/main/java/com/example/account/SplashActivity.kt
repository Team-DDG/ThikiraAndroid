package com.example.account

import android.os.Bundle
import androidx.lifecycle.observe
import com.dsm.androidcomponent.base.BaseActivity
import com.dsm.androidcomponent.ext.setupNavigateEvent
import com.example.account.databinding.ActivitySplashBinding
import com.example.account.viewmodel.SplashViewModel
import com.example.main.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override val layoutResId: Int
        get() = R.layout.activity_splash

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavigate()
        viewModel.finishActivityEvent.observe(this) { finish() }
    }

    private fun setupNavigate() {
        setupNavigateEvent<LoginActivity>(viewModel.startLoginEvent)
        setupNavigateEvent<MainActivity>(viewModel.startMainEvent)
    }
}