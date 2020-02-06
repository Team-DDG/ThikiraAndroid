package com.dsm.restaurant.viewModel

import com.dsm.restaurant.BaseTest
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.domain.interactor.GetRestaurantInfoUseCase
import com.dsm.restaurant.domain.model.RestaurantModel
import com.dsm.restaurant.presentation.ui.main.restaurant.RestaurantViewModel
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
            val response = RestaurantModel(
                image = "IMAGE",
                description = "DESCRIPTION",
                closeTime = "23:30",
                openTime = "10:30",
                offlinePayment = true,
                onlinePayment = true,
                dayOff = "DAY_OFF",
                minPrice = 1000,
                address = "ADDRESS",
                roadAddress = "ROAD_ADDRESS",
                area = "area1,area2,area3",
                category = "CATEGORY",
                name = "NAME",
                phone = "010-1111-2222",
                email = "EMAIL"
            )
            `when`(getRestaurantInfoUseCase.invoke(true))
                .thenReturn(response)

            loadRestaurantInfo(true)

            restaurantInfo.test().assertValue(response)
        }
    }

    @Test
    fun loadRestaurantInfoForbiddenTest() = runBlockingTest {
        viewModel.run {
            `when`(getRestaurantInfoUseCase.invoke(true))
                .thenThrow(ForbiddenException(Exception()))

            loadRestaurantInfo(true)

            toastEvent.test().assertValue(R.string.fail_forbidden)
        }
    }
}