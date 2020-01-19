package com.dsm.restaurant.presentation.ui.main.menu

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentMenuBinding
import com.dsm.restaurant.presentation.ui.adapter.MenuListAdapter
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import com.dsm.restaurant.trashModel.MenuModel
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : BaseFragment<FragmentMenuBinding>() {
    override val layoutResId: Int = R.layout.fragment_menu

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spn_menu.adapter = ArrayAdapter<String>(activity!!, R.layout.support_simple_spinner_dropdown_item, arrayOf("김치류", "빵류"))

        val adapter = MenuListAdapter()
        rv_menu.adapter = adapter

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

        rv_menu.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy < 0 && !fab_menu.isShown) {
                    fab_menu.show()
                } else if (dy > 0 && fab_menu.isShown) {
                    fab_menu.hide()
                }
            }
        })

        fab_menu.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_menuRegistration1Activity)
        }
    }
}