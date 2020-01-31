package com.dsm.restaurant.presentation.ui.register

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.restaurant.R
import com.dsm.restaurant.databinding.FragmentRegister2Binding
import com.dsm.restaurant.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_register2.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.DecimalFormat

class Register2Fragment : BaseFragment<FragmentRegister2Binding>() {
    override val layoutResId: Int = R.layout.fragment_register2

    private val viewModel: RegisterViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupNumberPicker()

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        tb_register2.setNavigationOnClickListener { findNavController().popBackStack() }

        btn_register2_next.setOnClickListener {
            findNavController().navigate(R.id.action_register2Fragment_to_register3Fragment)
        }

        btn_register2_category.setOnClickListener {
            findNavController().navigate(R.id.action_register2Fragment_to_categoryFragment)
        }
    }

    private fun setupNumberPicker() {
        val df = DecimalFormat("00")
        val hourArray = (0..23).map { df.format(it) }.toTypedArray()
        val minuteArray = (0..59).map { df.format(it) }.toTypedArray()

        np_register2_startHour.minValue = 0
        np_register2_startHour.maxValue = 23
        np_register2_startMinute.minValue = 0
        np_register2_startMinute.maxValue = 59

        np_register2_endHour.minValue = 0
        np_register2_endHour.maxValue = 23
        np_register2_endMinute.minValue = 0
        np_register2_endMinute.maxValue = 59

        np_register2_startHour.displayedValues = hourArray
        np_register2_startMinute.displayedValues = minuteArray

        np_register2_endHour.displayedValues = hourArray
        np_register2_endMinute.displayedValues = minuteArray
    }
}