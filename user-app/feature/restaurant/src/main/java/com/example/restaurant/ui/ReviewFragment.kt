package com.example.restaurant.ui

import android.os.Bundle
import android.view.View
import com.dsm.androidcomponent.base.BaseFragment
import com.example.restaurant.R
import com.example.restaurant.databinding.FragmentReviewBinding
import com.example.restaurant.viewmodel.RestaurantViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ReviewFragment: BaseFragment<FragmentReviewBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_review

    private val viewModel: RestaurantViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpSomething()
    }

    private fun setUpSomething() {

    }
}