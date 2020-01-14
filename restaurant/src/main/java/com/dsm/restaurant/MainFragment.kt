package com.dsm.restaurant

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.dsm.baseapp.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {
    override val layoutResId: Int = R.layout.fragment_main

    private val navGraphIds = listOf(
        R.navigation.nav_order,
        R.navigation.nav_menu,
        R.navigation.nav_coupon,
        R.navigation.nav_restaurant
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBnv()
    }

    private fun setupBnv() {
        addNavHostFragment()
        switchFragment(0)

        bnv_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.order -> switchFragment(0)
                R.id.menu -> switchFragment(1)
                R.id.coupon -> switchFragment(2)
                R.id.restaurant -> switchFragment(3)
            }
            true
        }

        bnv_main.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.order -> reselectFragment(0)
                R.id.menu -> reselectFragment(1)
                R.id.coupon -> reselectFragment(2)
                R.id.restaurant -> reselectFragment(3)
            }
        }
    }

    private fun addNavHostFragment() {
        navGraphIds.forEachIndexed { index, navGraphId ->
            getTransaction()
                .add(R.id.fragment_main_container, NavHostFragment.create(navGraphId), index.toString())
                .commitNow()
        }
    }

    private fun switchFragment(index: Int) {
        for (i in navGraphIds.indices) {
            val fragment = childFragmentManager.findFragmentByTag(i.toString())!!
            if (index == i) {
                getTransaction().show(fragment).commitNow()
            } else {
                getTransaction().hide(fragment).commitNow()
            }
        }
    }

    private fun reselectFragment(index: Int) {
        val fragment = childFragmentManager.findFragmentByTag(index.toString()) as NavHostFragment
        val navController = fragment.navController
        navController.popBackStack(navController.graph.startDestination, false)
    }

    private fun getTransaction() = childFragmentManager.beginTransaction()
        .setCustomAnimations(
            R.anim.nav_default_enter_anim,
            R.anim.nav_default_exit_anim,
            R.anim.nav_default_pop_enter_anim,
            R.anim.nav_default_pop_exit_anim
        )
}