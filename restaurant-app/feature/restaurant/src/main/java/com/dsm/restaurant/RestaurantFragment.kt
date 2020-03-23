package com.dsm.restaurant

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.androidcomponent.base.BaseFragment
import com.dsm.androidcomponent.ext.setupToastEvent
import com.dsm.restaurant.databinding.FragmentRestaurantBinding
import com.dsm.restaurant.viewModel.RestaurantViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RestaurantFragment : BaseFragment<FragmentRestaurantBinding>() {
    override val layoutResId: Int = R.layout.fragment_restaurant

    private val viewModel: RestaurantViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupToastEvent(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        binding.ivSetting.setOnClickListener {
            findNavController().navigate(R.id.settingFragment)
        }
    }
}