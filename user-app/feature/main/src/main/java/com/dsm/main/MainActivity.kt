package com.dsm.main

import android.os.Bundle
import androidx.appcompat.view.menu.MenuAdapter
import androidx.viewpager2.widget.ViewPager2
import com.dsm.androidcomponent.base.BaseActivity
import com.dsm.main.adapter.MainEventAdapter
import com.dsm.main.adapter.MainMenuAdapter
import com.dsm.main.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResId: Int
        get() = R.layout.activity_main

    private val vm by lazy { MainViewModel() }
    private val eventAdapter by lazy { MainEventAdapter() }
    private val menuAdapter by lazy { MainMenuAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpViewPager()
        setUpRecyclerView()
        binding.vm = vm
    }

    private fun setUpViewPager() {
        binding.vpMain.adapter = eventAdapter
        binding.vpMain.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    private fun setUpRecyclerView() {
        binding.rvMain.adapter = menuAdapter
        binding.rvMain.setHasFixedSize(true)
    }

}
