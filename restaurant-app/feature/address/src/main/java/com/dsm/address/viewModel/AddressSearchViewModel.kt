package com.dsm.address.viewModel

import androidx.lifecycle.*
import com.dsm.androidcomponent.R
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.model.Address
import com.dsm.model.repository.AddressRepository
import kotlinx.coroutines.launch

class AddressSearchViewModel(
    private val addressRepository: AddressRepository
) : ViewModel() {

    private val _selectedAddress = MutableLiveData<Address>()
    val selectedAddress: LiveData<Address> = _selectedAddress

    private val _addresses = MutableLiveData<List<Address>>()
    val addresses: LiveData<List<Address>> = _addresses

    // two-way binding
    val query = MutableLiveData<String>()

    private val _isSearching = MutableLiveData<Boolean>()
    val isSearching: LiveData<Boolean> = _isSearching

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _popEvent = SingleLiveEvent<Unit>()
    val popEvent: LiveData<Unit> = _popEvent

    val isAddressSearchEnable: LiveData<Boolean> = query.map { it != "" }

    fun onClickSearchAddress() = viewModelScope.launch {
        try {
            _isSearching.value = true
            val results = addressRepository.searchAddress(query.value ?: "")
            _addresses.value = results
        } catch (e: Exception) {
            _toastEvent.value = R.string.fail_exception_internal
        } finally {
            _isSearching.value = false
        }
    }

    fun onClickAddressItem(address: Address) {
        _selectedAddress.value = address
        _popEvent.call()
    }
}