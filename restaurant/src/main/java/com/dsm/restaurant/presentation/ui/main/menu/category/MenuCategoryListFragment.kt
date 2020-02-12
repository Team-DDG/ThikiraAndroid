package com.dsm.restaurant.presentation.ui.main.menu.category

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentMenuCategoryListBinding
import com.dsm.restaurant.domain.model.MenuCategoryModel
import com.dsm.restaurant.presentation.ui.adapter.MenuCategoryListAdapter
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_menu_category_list.*

class MenuCategoryListFragment : BaseFragment<FragmentMenuCategoryListBinding>() {
    override val layoutResId: Int = R.layout.fragment_menu_category_list

    val adapter = MenuCategoryListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupPopup()
        setupRecyclerView()
    }

    private fun setupNavigate() {
        iv_menu_category_list_back.setOnClickListener { findNavController().popBackStack() }

        requireActivity().onBackPressedDispatcher.addCallback(this) { findNavController().popBackStack() }
    }

    private fun setupRecyclerView() {
        rv_menu_category_list.adapter = adapter

        adapter.submitList(
            listOf(
                MenuCategoryModel(
                    menuCategoryId = 0,
                    name = "NAME"
                )
            )
        )
    }

    private fun setupPopup() {
        iv_menu_category_list_popup.setOnClickListener { view ->
            PopupMenu(activity, view).apply {
                menu.add(R.string.delete)

                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.title) {
                        getString(R.string.delete) ->
                            adapter.viewType = MenuCategoryListAdapter.DELETE_TYPE
                    }
                    true
                }

                show()
            }
        }
    }
}