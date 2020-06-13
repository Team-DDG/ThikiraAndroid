package com.dsm.order

import com.dsm.model.Order
import com.dsm.model.OrderDetailItem
import com.dsm.model.OrderGroupItem
import com.dsm.model.OrderOptionItem
import com.dsm.model.repository.OrderRepository
import com.dsm.order.viewModel.OrderListViewModel
import com.dsm.testcomponent.BaseTest
import com.example.error.exception.ForbiddenException
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.util.*

@ExperimentalCoroutinesApi
class OrderListViewModelTests : BaseTest() {

    @Mock
    private lateinit var orderRepository: OrderRepository

    private lateinit var viewModel: OrderListViewModel

    private val date = Date()

    @Before
    fun init() {
        viewModel = OrderListViewModel(orderRepository, date)
    }

    @Test
    fun loadOrdersForbiddenTest() = runBlockingTest {
        viewModel.run {
            `when`(orderRepository.getOrdersByDate(date))
                .thenThrow(ForbiddenException(Exception()))

            loadOrders(date)

            orders.test().assertValue(null)
            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
        }
    }

    @Test
    fun loadOrdersSuccessTest() = runBlockingTest {
        viewModel.run {
            val ordersResponse = listOf(
                Order(
                    "orderid",
                    Date(),
                    listOf(
                        OrderDetailItem(
                            "name", 10, 1, 1,
                            listOf(
                                OrderGroupItem(
                                    "name",
                                    listOf(OrderOptionItem("name", 1))
                                )
                            )
                        )
                    ),
                    "type", 100, "status", 100, "nick"
                )
            )
            `when`(orderRepository.getOrdersByDate(date)).thenReturn(ordersResponse)

            loadOrders(date)

            orders.test().assertValue(ordersResponse)
            toastEvent.test().assertNoValue()
        }
    }
}