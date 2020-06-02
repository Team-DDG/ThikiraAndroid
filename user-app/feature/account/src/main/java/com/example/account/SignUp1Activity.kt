package com.example.account

import android.os.Bundle
import com.dsm.androidcomponent.base.BaseActivity
import com.dsm.androidcomponent.ext.setupToastEvent
import com.example.account.databinding.ActivitySignUp1Binding
import com.example.account.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUp1Activity: BaseActivity<ActivitySignUp1Binding>(){
    override val layoutResId: Int
        get() = R.layout.activity_sign_up2

    private val viewModel : SignUpViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToastEvent(viewModel.toastEvent)

        binding.viewmodel = viewModel
    }
}