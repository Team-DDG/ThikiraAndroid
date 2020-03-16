package com.dsm.menu

import android.os.Bundle
import android.view.View
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.menu.databinding.FragmentMenuDetailBinding
import com.dsm.model.Menu
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class MenuDetailFragment : BaseFragment<FragmentMenuDetailBinding>() {
    override val layoutResId: Int = R.layout.fragment_menu_detail

    private val menu: Menu by lazy { arguments?.getSerializable("detail") as Menu }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = menu.name

        setupRecyclerView()

        binding.menu = menu
    }

    private fun setupRecyclerView() {
        binding.rvOption.adapter = GroupAdapter<GroupieViewHolder>()
    }
}
