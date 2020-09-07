package com.example.restaurant.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dsm.androidcomponent.base.BaseFragment
import com.example.restaurant.R
import com.example.restaurant.adapter.RestaurantMenuAdapter
import com.example.restaurant.databinding.FragmentMenuBinding
import com.example.restaurant.viewmodel.RestaurantViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_menu.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MenuFragment : BaseFragment<FragmentMenuBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_menu

    private val viewModel: RestaurantViewModel by sharedViewModel()
    private val menuAdapter by lazy { RestaurantMenuAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        recyclerview_menu.adapter = menuAdapter

        viewModel.getMenuCategory()
        if (viewModel.menuCategoryList.value != null) {
            viewModel.getMenu(viewModel.menuCategoryList.value!![0].menuCategoryId)
        }

        viewModel.menuCategoryList.observe(viewLifecycleOwner, Observer {
            it.map {
                tablayout_menu_category.addTab(tablayout_menu_category.newTab().setText(it.name))
            }
            tablayout_menu_category.onTabSelected { tab ->
                val category = it.filter {
                    it.name == tab?.text
                }
                viewModel.getMenu(category[0].menuCategoryId)
            }
            tablayout_menu_category.selectTab(tablayout_menu_category.getTabAt(0))
            viewModel.getMenu(viewModel.menuCategoryList.value?.get(0)!!.menuCategoryId)
        })
    }

    private fun TabLayout.onTabSelected(onTabSelected: (tab: TabLayout.Tab?) -> Unit) {
        this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                onTabSelected(tab)
            }
        })
    }
}

