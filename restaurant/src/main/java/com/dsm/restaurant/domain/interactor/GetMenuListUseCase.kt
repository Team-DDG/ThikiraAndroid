package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.MenuRepository
import com.dsm.restaurant.presentation.utilTesting.wrapEspressoIdlingResource

class GetMenuListUseCase(
    private val menuRepository: MenuRepository
) {
    suspend operator fun invoke(categoryName: String, forceUpdate: Boolean) = wrapEspressoIdlingResource {
        menuRepository.getMenuList(categoryName, forceUpdate)
    }
}