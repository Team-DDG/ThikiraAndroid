package com.dsm.coupon.viewModel

import androidx.lifecycle.*
import com.dsm.androidcomponent.R
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.error.exception.ForbiddenException
import com.dsm.error.exception.NotFoundException
import com.dsm.model.Coupon
import com.dsm.model.repository.CouponRepository
import kotlinx.coroutines.launch

class CouponViewModel(
    private val couponRepository: CouponRepository
) : ViewModel() {

    private val _forceUpdate = MutableLiveData(false)

    val coupons: LiveData<List<Coupon>> = _forceUpdate.switchMap { forceUpdate ->
        if (forceUpdate) {
            refreshCoupon()
        }
        couponRepository.observeCoupon()
    }

    val isCouponEmpty: LiveData<Boolean> = coupons.map { it.isNullOrEmpty() }

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    init {
        loadCoupons(true)
    }

    fun loadCoupons(forceUpdate: Boolean) {
        _forceUpdate.value = forceUpdate
    }

    private fun refreshCoupon() = viewModelScope.launch {
        try {
            couponRepository.refreshCoupons()
        } catch (e: Exception) {
            when (e) {
                is ForbiddenException -> _toastEvent.value = R.string.fail_exception_forbidden
                is NotFoundException -> Unit
                else -> _toastEvent.value = R.string.fail_exception_internal
            }
        }
    }
}