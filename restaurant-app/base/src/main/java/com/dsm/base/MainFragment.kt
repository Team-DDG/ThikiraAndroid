package com.dsm.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBnv()
    }

    private fun setupBnv() {
        bnv_main.setupWithNavController(
            listOf(R.navigation.nav_order, R.navigation.nav_menu, R.navigation.nav_coupon, R.navigation.nav_restaurant),
            childFragmentManager,
            R.id.fragment_nav_host,
            activity!!.intent
        )
    }

    fun hideBnv() {
        bnv_main.visibility = View.GONE
    }

    fun showBnv() {
        bnv_main.visibility = View.VISIBLE
    }
}