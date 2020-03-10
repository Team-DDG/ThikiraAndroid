package com.dsm.restaurant

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.restaurant.databinding.FragmentRestaurantBinding

class RestaurantFragment : BaseFragment<FragmentRestaurantBinding>() {
    override val layoutResId: Int = R.layout.fragment_restaurant

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivSetting.setOnClickListener {
            parentFragment!!.parentFragment!!.findNavController().navigate(R.id.settingFragment)
        }
    }
}