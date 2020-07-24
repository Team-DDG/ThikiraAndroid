package com.example.restaurant.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.dsm.androidcomponent.base.BaseActivity
import com.example.model.Restaurant
import com.example.restaurant.R
import com.example.restaurant.adapter.RestaurantAdapter
import com.example.restaurant.databinding.ActivityRestaurantBinding
import com.example.restaurant.viewmodel.RestaurantViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_restaurant.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantActivity : BaseActivity<ActivityRestaurantBinding>() {
    override val layoutResId: Int
        get() = R.layout.activity_restaurant

    private val viewModel: RestaurantViewModel by viewModel()
    private val tabText = arrayListOf(
        "정보",
        "메뉴",
        "리뷰"
    )
    private val fragmentList = arrayListOf(
        InfoFragment(),
        MenuFragment(),
        ReviewFragment()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpInfo()
        setUpTabLayout()

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
        binding.viewpagerRestaurant.adapter = RestaurantAdapter(this, fragmentList)

        TabLayoutMediator(tablayout_restaurant, viewpager_restaurant) { tab, position ->
            tab.text = tabText[position]
            viewpager_restaurant.setCurrentItem(tab.position, true)
        }.attach()
    }
}