package com.example.account

import android.os.Bundle
import com.dsm.androidcomponent.base.BaseActivity
import com.dsm.androidcomponent.ext.setupToastEvent
import com.example.account.databinding.ActivityRegister2Binding
import com.example.account.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class Register2Activity: BaseActivity<ActivityRegister2Binding>(){
    override val layoutResId: Int
        get() = R.layout.activity_register2

    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToastEvent(viewModel.toastEvent)

        binding.viewmodel = viewModel
    }
}