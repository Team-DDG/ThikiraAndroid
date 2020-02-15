package com.dsm.restaurant.presentation.ui.main.menu.registration

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentMenuRegistration2Binding
import com.dsm.restaurant.presentation.ui.adapter.MenuRegistrationOptionListAdapter
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import com.dsm.restaurant.presentation.util.setupToast
import kotlinx.android.synthetic.main.fragment_menu_registration2.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MenuRegistration2Fragment : BaseFragment<FragmentMenuRegistration2Binding>() {
    override val layoutResId: Int = R.layout.fragment_menu_registration2

    private val viewModel: MenuRegistrationViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupRecyclerView()
        setupToast(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        tb_menu_registration2.setNavigationOnClickListener { findNavController().popBackStack() }

        viewModel.dialogAddGroupEvent.observe(this) {
            findNavController().navigate(R.id.action_menuRegistration2Fragment_to_menuOptionAddGroupDialog)
        }

        viewModel.dialogAddOptionEvent.observe(this) {
            findNavController().navigate(
                R.id.action_menuRegistration2Fragment_to_menuOptionAddOptionDialog, bundleOf(
                    "position" to it
                )
            )
        }

        viewModel.finishActivityEvent.observe(this) { activity?.finish() }
    }

    private fun setupRecyclerView() {
        rv_menu_registration2_option.adapter = MenuRegistrationOptionListAdapter(viewModel)
        rv_menu_registration2_option.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
    }
}