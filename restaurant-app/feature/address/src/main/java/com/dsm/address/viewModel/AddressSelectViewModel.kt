package com.dsm.address.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dsm.model.Address

class AddressSelectViewModel : ViewModel() {

    private val _selectedAddress = MutableLiveData<Address>()
    val selectedAddress: LiveData<Address> = _selectedAddress

    fun selectAddress(address: Address) {
        _selectedAddress.value = address
    }
}