package com.example.restaurant.ui

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dsm.androidcomponent.base.BaseActivity
import com.example.model.Restaurant
import com.example.restaurant.R
import com.example.restaurant.adapter.RestaurantAdapter
import com.example.restaurant.databinding.ActivityRestaurantBinding
import com.example.restaurant.viewmodel.RestaurantViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_restaurant.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantActivity : BaseActivity<ActivityRestaurantBinding>() {
    override val layoutResId: Int
        get() = R.layout.activity_restaurant

    private val infoFragment = InfoFragment()
    private val menuFragment = MenuFragment()
    private val reviewFragment = ReviewFragment()

    private val viewModel: RestaurantViewModel by viewModel()
    private val tabText = arrayListOf(
        "정보",
        "메뉴",
        "리뷰"
    )
    private lateinit var fragmentList: ArrayList<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpInfo()
        setUpTabLayout()

        binding.viewModel = viewModel
        button_restaurant_back.setOnClickListener {
            finish()
        }
    }

    private fun setUpInfo() {
        val intent = intent
        if (intent.hasExtra("restaurant")) {
            val restaurantInfo = intent.extras?.get("restaurant") as Restaurant
            Glide.with(this).load(restaurantInfo.image).into(image_restaurant_main)
            val bundle = Bundle()
            bundle.putSerializable("restaurant", restaurantInfo)
            infoFragment.arguments = bundle

            fragmentList = arrayListOf(
                infoFragment,
                menuFragment,
                reviewFragment
            )
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