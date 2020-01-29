package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.AccountRepository

class CheckEmailUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(email: String) =
        accountRepository.checkEmail(email)
}