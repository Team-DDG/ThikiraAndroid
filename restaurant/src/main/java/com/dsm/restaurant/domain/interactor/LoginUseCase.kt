package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(body: Any) =
        authRepository.login(body)
}