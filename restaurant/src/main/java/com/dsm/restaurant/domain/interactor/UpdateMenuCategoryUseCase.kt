package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.MenuCategoryRepository
import com.dsm.restaurant.presentation.util.wrapEspressoIdlingResource

class UpdateMenuCategoryUseCase(
    private val menuCategoryRepository: MenuCategoryRepository
) {
    suspend operator fun invoke(menuCategoryId: Int, name: String) = wrapEspressoIdlingResource {
        menuCategoryRepository.updateMenuCategory(menuCategoryId, name)
    }
}
