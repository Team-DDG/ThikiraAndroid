package com.example.account

import android.os.Bundle
import androidx.lifecycle.observe
import com.dsm.androidcomponent.base.BaseActivity
import com.dsm.androidcomponent.ext.hideKeyboard
import com.dsm.androidcomponent.ext.setupToastEvent
import com.example.account.databinding.ActivityLoginBinding
import com.example.account.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override val layoutResId: Int
        get() = R.layout.activity_login

    private val viewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToastEvent(viewModel.toastEvent)

        viewModel.hideKeyEvent.observe(this) { hideKeyboard() }

        binding.viewmodel = viewModel
    }

}