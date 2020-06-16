package com.example.account

import android.os.Bundle
import androidx.lifecycle.observe
import com.dsm.androidcomponent.base.BaseActivity
import com.dsm.androidcomponent.ext.hideKeyboard
import com.dsm.androidcomponent.ext.setupNavigateEvent
import com.dsm.androidcomponent.ext.setupToastEvent
import com.dsm.androidcomponent.ext.startActivity
import com.dsm.main.MainActivity
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

        setupNavigateEvent(viewModel.navigateEvent)

        viewModel.hideKeyEvent.observe(this) { hideKeyboard() }

        viewModel.navigateEvent.observe(this) { startActivity<MainActivity>() }

        binding.viewmodel = viewModel
    }
}