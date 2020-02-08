package com.dsm.restaurant.presentation.ui.main.menu

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentMenuListBinding
import com.dsm.restaurant.presentation.ui.adapter.MenuListAdapter
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import com.dsm.restaurant.trashModel.MenuModel
import kotlinx.android.synthetic.main.fragment_menu_list.*

class MenuListFragment : BaseFragment<FragmentMenuListBinding>() {
    override val layoutResId: Int = R.layout.fragment_menu_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spn_menu.adapter = ArrayAdapter<String>(activity!!, R.layout.support_simple_spinner_dropdown_item, arrayOf("김치류", "빵류"))

        val adapter = MenuListAdapter()
        rv_menu_list.adapter = adapter

        adapter.submitList(
            (0..5).map {
                MenuModel(
                    menuId = it,
                    name = "name",
                    price = "10,000",
                    image = "image"
                )
            }
        )

        rv_menu_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0 && !fab_menu_list.isShown) {
                    fab_menu_list.show()
                } else if (dy > 0 && fab_menu_list.isShown) {
                    fab_menu_list.hide()
                }
            }
        })

        fab_menu_list.setOnClickListener {
            findNavController().navigate(R.id.action_menuListFragment_to_menuRegistration1Activity)
        }
    }
}