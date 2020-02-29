package com.dsm.account

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.dsm.account.databinding.FragmentRegister2Binding
import com.dsm.account.viewModel.RegisterViewModel
import com.dsm.androidcomponent.base.BaseFragment
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
        binding.btnSelectCategory.setOnClickListener {
            findNavController().navigate(R.id.action_register2Fragment_to_categorySelectFragment)
        }

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_register2Fragment_to_register3Fragment)
        }
    }

    private fun setupNumberPicker() {
        val df = DecimalFormat("00")
        val hourArray = (0..23).map { df.format(it) }.toTypedArray()
        val minuteArray = (0..59).map { df.format(it) }.toTypedArray()

        binding.run {
            npStartHour.minValue = 0
            npStartHour.maxValue = 23
            npStartMinute.minValue = 0
            npStartMinute.maxValue = 59

            npEndHour.minValue = 0
            npEndHour.maxValue = 23
            npEndMinute.minValue = 0
            npEndMinute.maxValue = 59

            npStartHour.displayedValues = hourArray
            npStartMinute.displayedValues = minuteArray

            npEndHour.displayedValues = hourArray
            npEndMinute.displayedValues = minuteArray
        }
    }
}