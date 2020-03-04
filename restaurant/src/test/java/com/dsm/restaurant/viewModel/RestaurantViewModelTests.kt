package com.dsm.restaurant.viewModel

import com.dsm.restaurant.BaseTest
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.domain.entity.RestaurantEntity
import com.dsm.restaurant.domain.interactor.GetRestaurantInfoUseCase
import com.dsm.restaurant.presentation.ui.restaurant.RestaurantViewModel
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class RestaurantViewModelTests : BaseTest() {

    @Mock
    private lateinit var getRestaurantInfoUseCase: GetRestaurantInfoUseCase

    private lateinit var viewModel: RestaurantViewModel

    @Before
    fun init() {
        viewModel = RestaurantViewModel(getRestaurantInfoUseCase)
    }

    @Test
    fun loadRestaurantInfoSuccessTest() = runBlockingTest {
        viewModel.run {
            val response = RestaurantEntity(
                image = "IMAGE",
                description = "DESCRIPTION",
                businessHour = "23:30~24:00",
                isOfflinePayment = true,
                isOnlinePayment = true,
                dayOff = "DAY_OFF",
                minPrice = 1000,
                address = "ADDRESS",
                roadAddress = "ROAD_ADDRESS",
                deliverableArea = "area1,area2,area3",
                category = "CATEGORY",
                name = "NAME",
                phone = "010-1111-2222",
                email = "EMAIL"
            )
            `when`(getRestaurantInfoUseCase.invoke(true))
                .thenReturn(response)

            loadRestaurantInfo(true)

            restaurantInfo.test().assertValue(response.toModel())
        }
    }

    @Test
    fun loadRestaurantInfoForbiddenTest() = runBlockingTest {
        viewModel.run {
            `when`(getRestaurantInfoUseCase.invoke(true))
                .thenThrow(ForbiddenException(Exception()))

            loadRestaurantInfo(true)

            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
        }
    }
}