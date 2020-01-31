package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.AccountRepository
import com.dsm.restaurant.presentation.util.wrapEspressoIdlingResource

class CheckEmailUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(email: String) = wrapEspressoIdlingResource {
        accountRepository.checkEmail(email)
    }
}