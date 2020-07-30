package com.example.restaurant.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import com.dsm.androidcomponent.base.BaseFragment
import com.example.model.Restaurant
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

        setInfo(arguments)

        binding.viewmodel = viewModel
    }

    //TODO: restaurant activity menu
    //TODO: restaurant activity scroll(better way to scroll)

    private fun setInfo(bundle: Bundle?) {
        if (bundle != null) {
            viewModel.setRestaurantInfo(bundle.getSerializable("restaurant") as Restaurant)
        }
    }
}