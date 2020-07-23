package com.example.restaurant.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dsm.androidcomponent.base.BaseActivity
import com.example.model.Restaurant
import com.example.restaurant.R
import com.example.restaurant.adapter.RestaurantAdapter
import com.example.restaurant.databinding.ActivityRestaurantBinding
import com.example.restaurant.viewmodel.RestaurantViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantActivity : BaseActivity<ActivityRestaurantBinding>() {
    override val layoutResId: Int
        get() = R.layout.activity_restaurant

    private val viewModel: RestaurantViewModel by viewModel()
    private val fragmentList = arrayListOf(
        InfoFragment(),
        MenuFragment(),
        ReviewFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpInfo()
        setUpTabLayout()
        binding.viewpagerRestaurant.adapter = RestaurantAdapter(this, fragmentList)

        binding.viewModel = viewModel
    }

    private fun setUpInfo() {
        val intent = intent
        if (intent.hasExtra("restaurant")) {
            val restaurantInfo = intent.extras?.get("restaurant") as Restaurant
            viewModel.setRestaurantInfo(restaurantInfo)
        } else {
            viewModel.failedToGetRestaurantInfo()
        }
    }

    private fun setUpTabLayout() {
        binding.tablayoutRestaurant.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.text) {
                    "정보" -> binding.viewpagerRestaurant.currentItem = 0
                    "메뉴" -> binding.viewpagerRestaurant.currentItem = 1
                    "리뷰" -> binding.viewpagerRestaurant.currentItem = 2
                }
            }

        })
    }
}