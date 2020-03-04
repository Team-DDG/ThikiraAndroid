package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.MenuCategoryRepository
import com.dsm.restaurant.presentation.utilTesting.wrapEspressoIdlingResource

class GetMenuCategoryListUseCase(
    private val menuCategoryRepository: MenuCategoryRepository
) {
    suspend operator fun invoke(forceUpdate: Boolean) = wrapEspressoIdlingResource {
        menuCategoryRepository.getMenuCategoryList(forceUpdate)
    }
}