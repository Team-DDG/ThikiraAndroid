package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.AccountRepository

class SearchAddressUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(query: String) =
        accountRepository.searchAddress(query)
}