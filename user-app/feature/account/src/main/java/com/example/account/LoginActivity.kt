package com.example.account

import android.os.Bundle
import androidx.lifecycle.observe
import com.dsm.androidcomponent.base.BaseActivity
import com.dsm.androidcomponent.ext.hideKeyboard
import com.dsm.androidcomponent.ext.setupNavigateEvent
import com.dsm.androidcomponent.ext.setupToastEvent
import com.example.main.MainActivity
import com.example.account.databinding.ActivityLoginBinding
import com.example.account.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override val layoutResId: Int
        get() = R.layout.activity_login

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToastEvent(viewModel.toastEvent)

        setupNavigate()

        viewModel.hideKeyEvent.observe(this) { hideKeyboard() }
        viewModel.finishLoginEvent.observe(this) { finish() }

        binding.viewmodel = viewModel
    }

    private fun setupNavigate() {
        setupNavigateEvent<MainActivity>(viewModel.navigateMainEvent)
        setupNavigateEvent<SignupActivity>(viewModel.navigateSignupEvent)
    }
}