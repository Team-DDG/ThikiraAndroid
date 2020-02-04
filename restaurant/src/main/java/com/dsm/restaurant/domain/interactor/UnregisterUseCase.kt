package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.AccountRepository
import com.dsm.restaurant.domain.repository.AuthRepository
import com.dsm.restaurant.presentation.util.wrapEspressoIdlingResource

class UnregisterUseCase(
    private val accountRepository: AccountRepository,
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(password: String) = wrapEspressoIdlingResource {
        authRepository.authPassword(password)
        accountRepository.unregister()
    }
}