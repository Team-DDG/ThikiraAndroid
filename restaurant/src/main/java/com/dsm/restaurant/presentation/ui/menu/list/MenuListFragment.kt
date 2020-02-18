package com.dsm.restaurant.presentation.ui.menu.list

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentMenuListBinding
import com.dsm.restaurant.presentation.ui.adapter.MenuListAdapter
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import com.dsm.restaurant.presentation.util.onItemSelectedListener
import com.dsm.restaurant.presentation.util.setupToast
import kotlinx.android.synthetic.main.fragment_menu_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuListFragment : BaseFragment<FragmentMenuListBinding>() {
    override val layoutResId: Int = R.layout.fragment_menu_list

    private val viewModel: MenuListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupSpinnerListener()
        setupPopupMenu()
        setupRecyclerView()
        setupToast(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        fab_menu_list.setOnClickListener {
            findNavController().navigate(R.id.action_menuListFragment_to_menuRegistration1Activity)
        }
    }

    private fun setupSpinnerListener() {
        spn_menu_list.onItemSelectedListener {
            viewModel.getMenuList(it, false)
        }
    }

    private fun setupPopupMenu() {
        iv_menu_list_popup.setOnClickListener {
            PopupMenu(activity, it).apply {
                menu.add(R.string.menu_category)

                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.title.toString()) {
                        getString(R.string.menu_category) ->
                            findNavController().navigate(R.id.action_menuListFragment_to_menuCategoryListFragment)
                    }
                    true
                }

                show()
            }
        }
    }

    private fun setupRecyclerView() {
        rv_menu_list.adapter = MenuListAdapter()

        rv_menu_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0 && !fab_menu_list.isShown) {
                    fab_menu_list.show()
                } else if (dy > 0 && fab_menu_list.isShown) {
                    fab_menu_list.hide()
                }
            }
        })
    }
}