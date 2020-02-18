package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.AccountRepository
import com.dsm.restaurant.domain.repository.AuthRepository
import com.dsm.restaurant.presentation.utilTesting.wrapEspressoIdlingResource

class UnregisterUseCase(
    private val accountRepository: AccountRepository,
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(password: String) = wrapEspressoIdlingResource {
        authRepository.confirmPassword(password)
        accountRepository.unregister()
    }
}