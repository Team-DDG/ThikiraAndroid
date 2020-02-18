package com.dsm.restaurant.presentation.ui.address

import androidx.lifecycle.*
import com.dsm.restaurant.R
import com.dsm.restaurant.domain.interactor.SearchAddressUseCase
import com.dsm.restaurant.domain.model.AddressModel
import com.dsm.restaurant.presentation.util.BusProvider
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch

class AddressSearchViewModel(
    private val searchAddressUseCase: SearchAddressUseCase
) : ViewModel() {

    val addressSearch = MutableLiveData<String>()

    val isAddressSearchClickable: LiveData<Boolean> = Transformations.map(addressSearch) { it != "" }

    private val _addressList = MutableLiveData<List<AddressModel>>()
    val addressList: LiveData<List<AddressModel>> = _addressList

    private val _isSearchingAddress = MutableLiveData<Boolean>()
    val isSearchingAddress: LiveData<Boolean> = _isSearchingAddress

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _popBackStackEvent = SingleLiveEvent<Unit>()
    val popBackStackEvent: LiveData<Unit> = _popBackStackEvent

    fun onClickSearchAddress() = viewModelScope.launch {
        _isSearchingAddress.value = true
        try {
            _addressList.value = searchAddressUseCase(addressSearch.value ?: "")
        } catch (e: Exception) {
            _toastEvent.value = R.string.fail_exception_internal
        } finally {
            _isSearchingAddress.value = false
        }
    }

    fun onClickAddressItem(addressModel: AddressModel) {
        BusProvider.getInstance().post(addressModel)
        _popBackStackEvent.call()
    }
}