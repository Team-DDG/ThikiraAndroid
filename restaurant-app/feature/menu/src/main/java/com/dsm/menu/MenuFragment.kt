package com.dsm.menu

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.onItemSelectedListener
import com.dsm.androidcomponent.ext.setupToastEvent
import com.dsm.menu.databinding.FragmentMenuBinding
import com.dsm.menu.viewModel.MenuViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel


class MenuFragment : BaseFragment<FragmentMenuBinding>() {
    override val layoutResId: Int = R.layout.fragment_menu

    private val viewModel: MenuViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupPopupMenu()
        setupSpinner()
        setupRecyclerView()
        setupToastEvent(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        binding.fabMenu.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_menuRegistration1Fragment)
        }
    }

    private fun setupPopupMenu() {
        binding.ivPopup.setOnClickListener {
            PopupMenu(activity, it).apply {
                menu.add(R.string.menu_category_setting)

                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.title.toString()) {
                        getString(R.string.menu_category_setting) ->
                            findNavController().navigate(R.id.action_menuFragment_to_menuCategoryFragment)
                    }
                    true
                }

                show()
            }
        }
    }

    private fun setupSpinner() {
        binding.spnMenu.onItemSelectedListener {
            viewModel.loadMenus(it)
        }
    }

    private fun setupRecyclerView() {
        binding.rvMenu.adapter = GroupAdapter<GroupieViewHolder>()

        binding.rvMenu.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0 && !binding.fabMenu.isShown) binding.fabMenu.show()
                else if (dy > 0 && binding.fabMenu.isShown) binding.fabMenu.hide()
            }
        })
    }
}