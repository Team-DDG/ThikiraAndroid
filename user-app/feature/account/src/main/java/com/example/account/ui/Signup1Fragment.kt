package com.example.account.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.setupToastEvent
import com.example.account.R
import com.example.account.databinding.FragmentSignup1Binding
import com.example.account.viewmodel.SignupViewModel
import kotlinx.android.synthetic.main.fragment_signup1.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class Signup1Fragment: BaseFragment<FragmentSignup1Binding>(){
    override val layoutResId: Int
        get() = R.layout.fragment_signup1

    private val viewModel : SignupViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        super.onCreate(savedInstanceState)

        setupToastEvent(viewModel.toastEvent)

        binding.viewmodel = viewModel

        btn_send_register1.setOnClickListener{
            viewModel.onClickStartVerify(activity!!)
        }

        btn_next_register1.setOnClickListener {
            findNavController().navigate(R.id.action_signup1Fragment_to_signup2Framgent)
        }
    }
}