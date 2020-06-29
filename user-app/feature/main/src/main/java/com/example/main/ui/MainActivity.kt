package com.example.main.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.dsm.androidcomponent.base.BaseActivity
import com.dsm.androidcomponent.ext.setupToastEvent
import com.dsm.main.R
import com.example.main.adapter.MainEventAdapter
import com.example.main.adapter.MainMenuAdapter
import com.dsm.main.databinding.ActivityMainBinding
import com.example.main.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResId: Int
        get() = R.layout.activity_main

    private val viewModel: MainViewModel by viewModel()
    private val eventAdapter by lazy { MainEventAdapter() }
    private val menuAdapter by lazy { MainMenuAdapter() }

    private var isActivityFocused = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpViewPager()
        setUpRecyclerView()
        setUpTabLayoutListener()
        setupToastEvent(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setUpViewPager() {
        binding.vpMain.adapter = eventAdapter
        binding.vpMain.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vpMain.currentItem = 1000

        autoScrollViewPager()
    }

    private fun setUpRecyclerView() {
        binding.rvMain.adapter = menuAdapter
        binding.rvMain.setHasFixedSize(true)
    }

    private fun setUpTabLayoutListener() {
        binding.tabMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewModel.getRestaurantList(tab.text.toString())
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun autoScrollViewPager() {
        lifecycleScope.launchWhenResumed {
            while (isActivityFocused) {
                delay(3000)
                binding.vpMain.currentItem += 1
            }
        }
    }


    override fun onPause() {
        super.onPause()
        isActivityFocused = false
    }

    override fun onResume() {
        super.onResume()
        isActivityFocused = true
    }
}
