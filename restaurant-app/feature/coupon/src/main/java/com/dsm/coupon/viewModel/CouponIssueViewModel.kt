package com.dsm.coupon.viewModel

import androidx.lifecycle.*
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.coupon.R
import com.dsm.error.exception.ForbiddenException
import com.dsm.model.repository.CouponRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CouponIssueViewModel(
    private val couponRepository: CouponRepository
) : ViewModel() {

    private val _expireDate = MutableLiveData<Date>()
    val expireDate: LiveData<Date> = _expireDate

    // two-way binding
    val price = MutableLiveData<String>()

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _dismissEvent = SingleLiveEvent<Unit>()
    val dismissEvent: LiveData<Unit> = _dismissEvent

    val expireDateText: LiveData<String> = Transformations.map(expireDate) {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        format.format(it)
    }

    val isIssueEnable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(expireDate) { value = !(expireDate.value == null || price.value.isNullOrBlank()) }
        addSource(price) { value = !(expireDate.value == null || price.value.isNullOrBlank()) }
    }

    fun setExpireDate(year: Int, month: Int, dayOfMonth: Int) {
        _expireDate.value = GregorianCalendar(year, month, dayOfMonth).time
    }

    fun onClickIssueCoupon() = viewModelScope.launch {
        try {
            couponRepository.issueCoupon(expireDate.value!!, price.value!!.toInt())

            _dismissEvent.call()
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }
}