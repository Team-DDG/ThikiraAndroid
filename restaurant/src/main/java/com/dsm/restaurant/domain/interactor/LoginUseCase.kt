package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.AuthRepository
import com.dsm.restaurant.presentation.utilTesting.wrapEspressoIdlingResource

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: Any) = wrapEspressoIdlingResource {
        authRepository.login(body)
    }
}