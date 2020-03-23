package com.dsm.base

import android.os.Bundle
import android.view.View
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.view.BnvViewModel
import com.dsm.base.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : BaseFragment<FragmentMainBinding>() {

    override val layoutResId: Int = R.layout.fragment_main

    private val bnvViewModel: BnvViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBnv()

        binding.bnvViewModel = bnvViewModel
    }

    private fun setupBnv() {
        bnv_main.setupWithNavController(
            listOf(R.navigation.nav_order, R.navigation.nav_menu, R.navigation.nav_coupon, R.navigation.nav_restaurant),
            childFragmentManager,
            R.id.fragment_nav_host,
            activity!!.intent
        )
    }
}