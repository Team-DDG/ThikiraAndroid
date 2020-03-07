package com.dsm.coupon.di

import com.dsm.coupon.viewModel.CouponIssueViewModel
import com.dsm.coupon.viewModel.CouponViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val couponModule = module {

    viewModel { CouponIssueViewModel(get()) }

    viewModel { CouponViewModel(get()) }
}