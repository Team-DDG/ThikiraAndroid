package com.example.restaurant.ui

import android.os.Bundle
import android.view.View
import com.dsm.androidcomponent.base.BaseFragment
import com.example.restaurant.R
import com.example.restaurant.databinding.FragmentInfoBinding
import com.example.restaurant.viewmodel.RestaurantViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class InfoFragment: BaseFragment<FragmentInfoBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_info

    private val viewModel: RestaurantViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}