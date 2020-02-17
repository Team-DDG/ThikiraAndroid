package com.dsm.restaurant.di.address

import com.dsm.restaurant.data.dataSource.AddressDataSource
import com.dsm.restaurant.data.dataSource.AddressDataSourceImpl
import com.dsm.restaurant.data.repository.AddressRepositoryImpl
import com.dsm.restaurant.domain.interactor.SearchAddressUseCase
import com.dsm.restaurant.domain.repository.AddressRepository
import org.koin.dsl.module

val addressModule = module {
    factory<AddressDataSource> { AddressDataSourceImpl(get(), get()) }

    factory<AddressRepository> { AddressRepositoryImpl(get()) }

    factory { SearchAddressUseCase(get()) }
}