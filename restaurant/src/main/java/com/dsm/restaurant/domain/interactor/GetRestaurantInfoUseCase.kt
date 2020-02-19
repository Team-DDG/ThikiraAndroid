package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.RestaurantRepository
import com.dsm.restaurant.presentation.utilTesting.wrapEspressoIdlingResource

class GetRestaurantInfoUseCase(
    private val restaurantRepository: RestaurantRepository
) {
    suspend operator fun invoke(forceUpdate: Boolean) = wrapEspressoIdlingResource {
        restaurantRepository.getRestaurantInfo(forceUpdate)
    }
}