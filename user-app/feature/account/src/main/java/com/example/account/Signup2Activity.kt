package com.example.account

import android.os.Bundle
import com.dsm.androidcomponent.base.BaseActivity
import com.dsm.androidcomponent.ext.setupToastEvent
import com.example.account.databinding.ActivitySignup2Binding
import com.example.account.viewmodel.SignupViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class Signup2Activity: BaseActivity<ActivitySignup2Binding>(){
    override val layoutResId: Int
        get() = R.layout.activity_signup2

    private val viewModel: SignupViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToastEvent(viewModel.toastEvent)

        binding.viewmodel = viewModel
    }
}