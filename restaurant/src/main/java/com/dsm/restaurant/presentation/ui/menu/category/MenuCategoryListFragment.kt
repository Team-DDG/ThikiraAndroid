package com.dsm.restaurant.presentation.ui.menu.category

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.activity.addCallback
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentMenuCategoryListBinding
import com.dsm.restaurant.presentation.ui.adapter.MenuCategoryListAdapter
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import com.dsm.restaurant.presentation.util.setupToast
import kotlinx.android.synthetic.main.fragment_menu_category_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuCategoryListFragment : BaseFragment<FragmentMenuCategoryListBinding>() {
    override val layoutResId: Int = R.layout.fragment_menu_category_list

    private val viewModel: MenuCategoryListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupRecyclerView()
        setupPopup()
        setupViewType()
        setupToast(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        iv_menu_category_list_back.setOnClickListener { findNavController().popBackStack() }

        requireActivity().onBackPressedDispatcher.addCallback(this) { findNavController().popBackStack() }

        viewModel.popBackStackEvent.observe(this) {
            findNavController().popBackStack(R.id.menuRegistration1Fragment, false)
        }
    }

    private fun setupRecyclerView() {
        rv_menu_category_list.adapter = MenuCategoryListAdapter(viewModel)
        rv_menu_category_list.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
    }

    private fun setupPopup() {
        iv_menu_category_list_popup.setOnClickListener { view ->
            PopupMenu(activity, view).apply {
                menu.add(R.string.delete)
                menu.add(R.string.update)

                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.title) {
                        getString(R.string.delete) -> viewModel.onClickStartDelete()
                        getString(R.string.update) -> viewModel.onClickStartUpdate()
                    }
                    true
                }

                show()
            }
        }
    }

    private fun setupViewType() {
        viewModel.changeViewTypeEvent.observe(this) {
            (rv_menu_category_list.adapter as MenuCategoryListAdapter).listViewType = it
        }
    }
}