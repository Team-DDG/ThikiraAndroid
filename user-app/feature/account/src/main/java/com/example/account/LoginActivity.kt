package com.example.account

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.observe
import com.dsm.androidcomponent.base.BaseActivity
import com.dsm.androidcomponent.ext.hideKeyboard
import com.dsm.androidcomponent.ext.setupToastEvent
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

        binding.tvRegisterLogin.setOnClickListener {
            startActivity(Intent(this, Register1Activity::class.java))
        }

        viewModel.hideKeyEvent.observe(this) { hideKeyboard() }

        binding.viewmodel = viewModel
    }
}