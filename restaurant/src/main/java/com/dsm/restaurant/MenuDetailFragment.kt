package com.dsm.restaurant

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.dsm.baseapp.BaseFragment
import kotlinx.android.synthetic.main.fragment_menu_detail.*

class MenuDetailFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_menu_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tb_menuDetail.setNavigationOnClickListener { findNavController().popBackStack() }

        requireActivity().onBackPressedDispatcher.addCallback(this) { findNavController().popBackStack() }

        val adapter = MenuOptionListAdapter()
        rv_menu_option.adapter = adapter
        adapter.optionItems = listOf(
            OptionModel("김 추가", "1,000"),
            OptionModel("파 추가", "2,000")
        )

    }
}