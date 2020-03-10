package com.dsm.setting.viewModel

import androidx.lifecycle.*
import com.dsm.androidcomponent.R
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.error.exception.ForbiddenException
import com.dsm.model.Address
import com.dsm.model.repository.AddressRepository
import com.dsm.model.repository.RestaurantRepository
import kotlinx.coroutines.launch

class AddressChangeViewModel(
    private val restaurantRepository: RestaurantRepository,
    private val addressRepository: AddressRepository
) : ViewModel() {

    private val _currentAddress = MutableLiveData<Address>()
    val currentAddress: LiveData<Address> = _currentAddress

    private val _newAddress = MutableLiveData<Address>()
    val newAddress: LiveData<Address> = _newAddress

    val isChangeAddressEnable: LiveData<Boolean> = Transformations.map(newAddress) { it != null }

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _popEvent = SingleLiveEvent<Unit>()
    val popEvent: LiveData<Unit> = _popEvent

    init {
        loadAddress()
    }

    fun setNewAddress(newAddress: Address) {
        _newAddress.value = newAddress
    }

    fun onClickChangeAddress() = viewModelScope.launch {
        try {
            addressRepository.changeAddress(newAddress.value!!.address, newAddress.value!!.roadAddress)

            _popEvent.call()
            _newAddress.value = null
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }

    private fun loadAddress() = viewModelScope.launch {
        _currentAddress.value = restaurantRepository.getAddress()
    }
}