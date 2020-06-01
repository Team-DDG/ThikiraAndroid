package com.dsm.main

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.dsm.androidcomponent.base.BaseActivity
import com.dsm.main.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResId: Int
        get() = R.layout.activity_main

    private val vm by lazy { MainViewModel() }
    private val eventAdapter by lazy { MainEventAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vpMain.adapter = eventAdapter
        binding.vpMain.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vm = vm
    }
}
