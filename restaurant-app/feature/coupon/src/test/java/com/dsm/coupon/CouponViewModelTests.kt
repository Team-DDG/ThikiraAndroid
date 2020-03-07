package com.dsm.coupon

import androidx.lifecycle.liveData
import com.dsm.coupon.viewModel.CouponViewModel
import com.dsm.model.Coupon
import com.dsm.model.repository.CouponRepository
import com.dsm.testcomponent.BaseTest
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verifyNoInteractions
import java.util.*

@ExperimentalCoroutinesApi
class CouponViewModelTests : BaseTest() {

    @Mock
    private lateinit var couponRepository: CouponRepository

    private lateinit var viewModel: CouponViewModel

    @Before
    fun init() {
        viewModel = CouponViewModel(couponRepository)
    }

    @Test
    fun refreshCouponSuccessTest() = runBlockingTest {
        viewModel.run {
            val response = listOf(Coupon(Date(), 100, true))
            `when`(couponRepository.refreshCoupons()).thenReturn(Unit)
            `when`(couponRepository.observeCoupon()).thenReturn(liveData { emit(response) })

            loadCoupons(true)

            coupons.test().assertValue(response)
        }
    }

    @Test
    fun whenForceUpdateFalse() = runBlockingTest {
        viewModel.run {
            val response = listOf(Coupon(Date(), 100, true))
            `when`(couponRepository.observeCoupon()).thenReturn(liveData { emit(response) })

            loadCoupons(false)

            verifyNoInteractions(couponRepository)
            coupons.test().assertValue(response)
        }
    }
}