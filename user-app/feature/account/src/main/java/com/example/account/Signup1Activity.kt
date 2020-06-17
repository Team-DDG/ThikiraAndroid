package com.example.account

import android.os.Bundle
import com.dsm.androidcomponent.base.BaseActivity
import com.dsm.androidcomponent.ext.setupNavigateEvent
import com.dsm.androidcomponent.ext.setupToastEvent
import com.example.account.databinding.ActivitySignup1Binding
import com.example.account.viewmodel.SignupViewModel
import kotlinx.android.synthetic.main.activity_signup1.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class Signup1Activity: BaseActivity<ActivitySignup1Binding>(){
    override val layoutResId: Int
        get() = R.layout.activity_signup1

    private val viewModel : SignupViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToastEvent(viewModel.toastEvent)

        setupNavigateEvent<Signup2Activity>(viewModel.navigateSignup2Event)

        binding.viewmodel = viewModel

        btn_send_register1.setOnClickListener{
            viewModel.onClickStartVerify(this)
        }
    }
}