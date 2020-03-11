package com.dsm.order.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.error.exception.ForbiddenException
import com.dsm.model.Order
import com.dsm.model.repository.OrderRepository
import com.dsm.order.R
import kotlinx.coroutines.launch
import java.util.*

class OrderListViewModel(
    private val orderRepository: OrderRepository,
    date: Date
) : ViewModel() {

    private val _orders = MutableLiveData<List<Order>>()
    val orders: LiveData<List<Order>> = _orders

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    init {
        loadOrders(date)
    }

    fun loadOrders(date: Date) = viewModelScope.launch {
        try {
            _orders.value = orderRepository.getOrdersByDate(date)
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }
}