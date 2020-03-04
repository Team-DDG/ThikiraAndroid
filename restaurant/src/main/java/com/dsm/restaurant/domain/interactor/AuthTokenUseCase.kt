package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.AuthRepository
import com.dsm.restaurant.presentation.utilTesting.wrapEspressoIdlingResource

class AuthTokenUseCase(
    private val authTokenUseCase: AuthRepository
) {
    suspend operator fun invoke() = wrapEspressoIdlingResource {
        authTokenUseCase.authToken()
    }
}