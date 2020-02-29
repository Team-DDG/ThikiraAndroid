package com.dsm.base.di

import com.dsm.address.viewModel.AddressSearchViewModel
import com.dsm.api.dataSource.RemoteAddressDataSource
import com.dsm.api.dataSource.RemoteAddressDataSourceImpl
import com.dsm.model.repository.AddressRepository
import com.dsm.repository.AddressRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addressModule = module {

    factory<RemoteAddressDataSource> { RemoteAddressDataSourceImpl(get()) }

    factory<AddressRepository> { AddressRepositoryImpl(get()) }

    viewModel { AddressSearchViewModel(get()) }
}