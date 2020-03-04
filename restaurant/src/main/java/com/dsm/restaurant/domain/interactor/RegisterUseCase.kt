package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.AccountRepository

class RegisterUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(body: Any) =
        accountRepository.register(body)
}