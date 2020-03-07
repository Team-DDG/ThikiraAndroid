package com.dsm.coupon.di

import com.dsm.coupon.viewModel.CouponIssueViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val couponModule = module {

    viewModel { CouponIssueViewModel(get()) }
}