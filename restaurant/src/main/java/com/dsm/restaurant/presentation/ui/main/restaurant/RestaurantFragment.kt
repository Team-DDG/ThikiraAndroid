package com.dsm.restaurant.presentation.ui.main.restaurant

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentRestaurantBinding
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_restaurant.*

class RestaurantFragment : BaseFragment<FragmentRestaurantBinding>() {
    override val layoutResId: Int = R.layout.fragment_restaurant

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iv_restaurant_setting.setOnClickListener {
            findNavController().navigate(R.id.action_restaurantFragment_to_settingActivity)
        }
    }
}