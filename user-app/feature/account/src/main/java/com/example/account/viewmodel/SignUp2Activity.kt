package com.example.account.viewmodel

import android.os.Bundle
import com.dsm.androidcomponent.base.BaseActivity
import com.dsm.androidcomponent.ext.setupToastEvent
import com.example.account.R
import com.example.account.databinding.ActivitySignUp2Binding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUp2Activity: BaseActivity<ActivitySignUp2Binding>(){
    override val layoutResId: Int
        get() = R.layout.activity_sign_up2

    private val viewModel: SignUpViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToastEvent(viewModel.toastEvent)

        binding.viewmodel = viewModel
    }
}