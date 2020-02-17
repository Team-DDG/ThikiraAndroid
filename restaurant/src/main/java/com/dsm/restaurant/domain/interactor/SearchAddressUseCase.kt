package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.AddressRepository
import com.dsm.restaurant.presentation.util.wrapEspressoIdlingResource

class SearchAddressUseCase(
    private val addressRepository: AddressRepository
) {
    suspend operator fun invoke(query: String) = wrapEspressoIdlingResource {
        addressRepository.searchAddress(query)
    }
}