package com.dsm.order.di

import com.dsm.order.viewModel.OrderListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.*

val orderModule = module {

    viewModel { (date: Date) -> OrderListViewModel(get(), date) }
}