package com.dsm.restaurant.presentation.ui.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentRegister1Binding
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_register1.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class Register1Fragment : BaseFragment<FragmentRegister1Binding>() {
    override val layoutResId: Int = R.layout.fragment_register1

    private val viewModel: RegisterViewModel by sharedViewModel(from = {
        findNavController().getViewModelStoreOwner(R.id.nav_graph)
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tb_register1.setNavigationOnClickListener { findNavController().popBackStack() }

        btn_register1_next.setOnClickListener {
            findNavController().navigate(R.id.action_register1Fragment_to_register2Fragment)
        }

        btn_register1_location.setOnClickListener {
            findNavController().navigate(R.id.action_register1Fragment_to_locationFragment)
        }

        binding.viewModel = viewModel
    }
}