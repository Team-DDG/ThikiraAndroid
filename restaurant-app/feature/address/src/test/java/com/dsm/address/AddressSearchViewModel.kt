package com.dsm.address

import com.dsm.address.viewModel.AddressSearchViewModel
import com.dsm.model.Address
import com.dsm.model.repository.AddressRepository
import com.dsm.testcomponent.BaseTest
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class AddressSearchViewModelTests : BaseTest() {

    @Mock
    private lateinit var addressRepository: AddressRepository

    private lateinit var viewModel: AddressSearchViewModel

    @Before
    fun init() {
        viewModel = AddressSearchViewModel(addressRepository)
    }

    @Test
    fun addressSearchSuccessTest() = runBlockingTest {
        viewModel.run {
            val response = listOf(Address("TITLE", "ADDRESS", "ROAD_ADDRESS"))
            query.value = "Hello"
            `when`(addressRepository.searchAddress(query.value ?: ""))
                .thenReturn(response)

            onClickSearchAddress()

            addresses.test().assertValue(response)
        }
    }

    @Test
    fun addressSearchFailedTest() = runBlockingTest {
        viewModel.run {
            query.value = "Hello"
            `when`(addressRepository.searchAddress(query.value ?: ""))
                .thenThrow(RuntimeException())

            onClickSearchAddress()

            addresses.test().assertNoValue()
            toastEvent.test().assertValue(R.string.fail_exception_internal)
        }
    }
}