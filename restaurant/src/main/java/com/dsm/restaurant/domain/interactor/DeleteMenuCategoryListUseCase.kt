package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.MenuCategoryRepository
import com.dsm.restaurant.presentation.util.wrapEspressoIdlingResource

class DeleteMenuCategoryListUseCase(
    private val menuCategoryRepository: MenuCategoryRepository
) {
    suspend operator fun invoke(menuCategoryList: List<Int>) = wrapEspressoIdlingResource {
        menuCategoryRepository.deleteMenuCategoryList(menuCategoryList)
    }
}