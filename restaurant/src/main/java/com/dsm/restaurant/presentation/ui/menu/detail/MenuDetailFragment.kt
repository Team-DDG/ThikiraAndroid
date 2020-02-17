package com.dsm.restaurant.presentation.ui.menu.detail

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentMenuDetailBinding
import com.dsm.restaurant.presentation.ui.adapter.MenuOptionListAdapter
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import com.dsm.restaurant.trashModel.OptionModel
import kotlinx.android.synthetic.main.fragment_menu_detail.*

class MenuDetailFragment : BaseFragment<FragmentMenuDetailBinding>() {
    override val layoutResId: Int = R.layout.fragment_menu_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tb_menu_detail.setNavigationOnClickListener { findNavController().popBackStack() }

        requireActivity().onBackPressedDispatcher.addCallback(this) { findNavController().popBackStack() }

        val adapter = MenuOptionListAdapter()
        rv_menu_detail_option.adapter = adapter
        adapter.optionItems = listOf(
            OptionModel("김 추가", "1,000"),
            OptionModel("파 추가", "2,000")
        )

    }
}