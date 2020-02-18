package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.AccountRepository
import com.dsm.restaurant.presentation.utilTesting.wrapEspressoIdlingResource

class ConfirmEmailDuplicationUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(email: String) = wrapEspressoIdlingResource {
        accountRepository.confirmEmailDuplication(email)
    }
}