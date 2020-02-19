package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.MenuRepository
import com.dsm.restaurant.presentation.utilTesting.wrapEspressoIdlingResource

class UploadMenuUseCase(
    private val menuRepository: MenuRepository
) {
    suspend operator fun invoke(body: Any) = wrapEspressoIdlingResource {
        menuRepository.uploadMenu(body)
    }
}