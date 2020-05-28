package com.dsm.coupon

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.dsm.androidcomponent.base.BaseDialog
import com.dsm.androidcomponent.ext.setupToastEvent
import com.dsm.coupon.databinding.DialogCouponIssueBinding
import com.dsm.coupon.viewModel.CouponIssueViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CouponIssueDialog : BaseDialog<DialogCouponIssueBinding>() {
    override val layoutResId: Int = R.layout.dialog_coupon_issue

    private val viewModel: CouponIssueViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigate()
        setupDatePicker()
        setupToastEvent(viewModel.toastEvent)

        binding.viewModel = viewModel
    }

    private fun setupNavigate() {
        viewModel.dismissEvent.observe(viewLifecycleOwner, Observer { dismiss() })
    }

    private fun setupDatePicker() {
        binding.tvPeriodSettingLabel.setOnClickListener {
            val listener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                viewModel.setExpireDate(year, month, dayOfMonth)
            }
            val now = Calendar.getInstance()
            DatePickerDialog(context!!, R.style.AppTheme, listener, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
}