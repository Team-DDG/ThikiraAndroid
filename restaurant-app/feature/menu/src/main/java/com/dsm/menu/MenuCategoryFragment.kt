package com.dsm.menu

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.setupToastEvent
import com.dsm.menu.binding.MenuCategoryViewType
import com.dsm.menu.databinding.FragmentMenuCategoryBinding
import com.dsm.menu.viewModel.MenuCategorySelectViewModel
import com.dsm.menu.viewModel.MenuCategoryViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuCategoryFragment : BaseFragment<FragmentMenuCategoryBinding>() {
    override val layoutResId: Int = R.layout.fragment_menu_category

    private val viewModel: MenuCategoryViewModel by viewModel()
    private val menuCategorySelectViewModel: MenuCategorySelectViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupRecyclerView()
        setupPopupMenu()
        setupToastEvent(viewModel.toastEvent)

        binding.viewModel = viewModel
        binding.menuCategorySelectViewModel = menuCategorySelectViewModel
    }

    private fun setupNavigate() {
        binding.ivBack.setOnClickListener { findNavController().popBackStack() }

        menuCategorySelectViewModel.popToRegistrationEvent.observe(this) {
            findNavController().popBackStack(R.id.menuRegistration1Fragment, false)
        }
    }

    private fun setupRecyclerView() {
        binding.rvMenuCategory.adapter = GroupAdapter<GroupieViewHolder>()
    }

    private fun setupPopupMenu() {
        binding.ivPopup.setOnClickListener { view ->
            PopupMenu(activity, view).apply {
                menu.add(R.string.delete)
                menu.add(R.string.update)

                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.title) {
                        getString(R.string.delete) -> viewModel.setViewType(MenuCategoryViewType.DELETE)
                        getString(R.string.update) -> viewModel.setViewType(MenuCategoryViewType.UPDATE)
                    }
                    true
                }

                show()
            }
        }
    }
}