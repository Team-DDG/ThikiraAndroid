package com.dsm.setting

import com.dsm.androidcomponent.R
import com.dsm.error.exception.ForbiddenException
import com.dsm.model.Address
import com.dsm.model.repository.AddressRepository
import com.dsm.model.repository.RestaurantRepository
import com.dsm.setting.viewModel.AddressChangeViewModel
import com.dsm.testcomponent.BaseTest
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class AddressChangeViewModelTests : BaseTest() {

    @Mock
    private lateinit var restaurantRepository: RestaurantRepository

    @Mock
    private lateinit var addressRepository: AddressRepository

    private lateinit var viewModel: AddressChangeViewModel

    @Before
    fun init() {
        viewModel = AddressChangeViewModel(restaurantRepository, addressRepository)
    }

    @Test
    fun changeAddressForbiddenTest() = runBlockingTest {
        viewModel.run {
            setNewAddress(Address("TITLE", "ADDRESS", "ROAD_ADDRESS"))
            `when`(addressRepository.changeAddress(newAddress.value!!.address, newAddress.value!!.roadAddress))
                .thenThrow(ForbiddenException(Exception()))

            onClickChangeAddress()

            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
            popEvent.test().assertNoValue()
        }
    }

    @Test
    fun changeAddressSuccessTest() = runBlockingTest {
        viewModel.run {
            setNewAddress(Address("TITLE", "ADDRESS", "ROAD_ADDRESS"))
            `when`(addressRepository.changeAddress(newAddress.value!!.address, newAddress.value!!.roadAddress)).thenReturn(Unit)

            onClickChangeAddress()

            toastEvent.test().assertNoValue()
            popEvent.test().assertHasValue()
            newAddress.test().assertValue(null)
        }
    }
}