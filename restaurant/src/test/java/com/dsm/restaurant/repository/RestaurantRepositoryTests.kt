package com.dsm.restaurant.repository

import com.dsm.restaurant.data.dataSource.RestaurantDataSource
import com.dsm.restaurant.data.local.dto.RestaurantLocalDto
import com.dsm.restaurant.data.remote.dto.RestaurantDto
import com.dsm.restaurant.data.repository.RestaurantRepositoryImpl
import com.dsm.restaurant.domain.repository.RestaurantRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RestaurantRepositoryTests {

    @Mock
    private lateinit var restaurantDataSource: RestaurantDataSource

    private lateinit var repository: RestaurantRepository

    private val restaurantDto = RestaurantDto(
        image = "IMAGE",
        name = "NAME",
        category = "CATEGORY",
        area = arrayOf("A"),
        roadAddress = "ROAD_ADDR",
        address = "ADDRESS",
        minPrice = 1000,
        dayOff = "DAY_OFF",
        onlinePayment = true,
        offlinePayment = false,
        openTime = "10:30",
        closeTime = "10:30",
        description = "DESCRIPTION",
        email = "EMAIL",
        phone = "PHONE"
    )

    private val restaurantLocalDto = RestaurantLocalDto(
        image = "IMAGE",
        name = "NAME",
        category = "CATEGORY",
        area = "AREA",
        roadAddress = "ROAD_ADDR",
        address = "ADDRESS",
        minPrice = 1000,
        dayOff = "DAY_OFF",
        onlinePayment = true,
        offlinePayment = false,
        openTime = "10:30",
        closeTime = "10:30",
        description = "DESCRIPTION",
        email = "EMAIL",
        phone = "PHONE"
    )

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        repository = RestaurantRepositoryImpl(restaurantDataSource)
    }

    @Test
    fun `when force update false, local restaurant info is empty test`() {
        runBlocking {
            `when`(restaurantDataSource.getLocalRestaurantInfo()).thenReturn(null)
            `when`(restaurantDataSource.getRemoteRestaurantInfo()).thenReturn(restaurantDto)

            repository.getRestaurantInfo(false)

            verify(restaurantDataSource).getRemoteRestaurantInfo()
            verify(restaurantDataSource).insertLocalRestaurantInfo(restaurantDto.toLocalDto())
        }
    }

    @Test
    fun `when force update false, local restaurant info is not empty test`() {
        runBlocking {
            `when`(restaurantDataSource.getLocalRestaurantInfo()).thenReturn(restaurantLocalDto)
            `when`(restaurantDataSource.getRemoteRestaurantInfo()).thenReturn(restaurantDto)

            repository.getRestaurantInfo(false)

            verify(restaurantDataSource).getLocalRestaurantInfo()
            verifyNoMoreInteractions(restaurantDataSource)
        }
    }
}