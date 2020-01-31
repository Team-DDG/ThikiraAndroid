package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.AccountRepository
import com.dsm.restaurant.presentation.util.wrapEspressoIdlingResource

class SearchAddressUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(query: String) = wrapEspressoIdlingResource {
        accountRepository.searchAddress(query)
    }
}