package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.AuthRepository
import com.dsm.restaurant.presentation.utilTesting.wrapEspressoIdlingResource

class ConfirmEmailDuplicationUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String) = wrapEspressoIdlingResource {
        authRepository.confirmEmailDuplication(email)
    }
}