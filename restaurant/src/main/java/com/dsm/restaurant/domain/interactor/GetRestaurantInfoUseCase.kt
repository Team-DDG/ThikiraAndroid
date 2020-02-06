package com.dsm.restaurant.domain.interactor

import com.dsm.restaurant.domain.repository.RestaurantRepository
import com.dsm.restaurant.presentation.util.wrapEspressoIdlingResource

class GetRestaurantInfoUseCase(
    private val restaurantRepository: RestaurantRepository
) {
    suspend operator fun invoke(forceUpdate: Boolean) = wrapEspressoIdlingResource {
        restaurantRepository.getRestaurantInfo(forceUpdate)
    }
}