package com.dsm.menu

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.setupToastEvent
import com.dsm.menu.adapter.MenuOptionListAdapter
import com.dsm.menu.databinding.FragmentMenuRegistration2Binding
import com.dsm.menu.viewModel.MenuRegistrationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MenuRegistration2Fragment : BaseFragment<FragmentMenuRegistration2Binding>() {
    override val layoutResId: Int = R.layout.fragment_menu_registration2

    private val viewModel: MenuRegistrationViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupRecyclerView()
        setupToastEvent(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        viewModel.popToMainEvent.observe(this) {
            findNavController().popBackStack(R.id.mainFragment, false)
        }
    }

    private fun setupRecyclerView() {
        binding.rvOption.adapter = MenuOptionListAdapter(viewModel)
    }
}