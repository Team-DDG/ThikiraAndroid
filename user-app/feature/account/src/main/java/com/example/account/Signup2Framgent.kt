package com.example.account

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.setupToastEvent
import com.example.account.databinding.FragmentSignup2Binding
import com.example.account.viewmodel.SignupViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class Signup2Framgent: BaseFragment<FragmentSignup2Binding>(){
    override val layoutResId: Int
        get() = R.layout.fragment_signup2

    private val viewModel: SignupViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToastEvent(viewModel.toastEvent)

        setupNavigate()

        binding.viewmodel = viewModel
    }

    private fun setupNavigate() {
        viewModel.popToLoginEvent.observe(this){
            activity!!.finish()
        }
    }
}