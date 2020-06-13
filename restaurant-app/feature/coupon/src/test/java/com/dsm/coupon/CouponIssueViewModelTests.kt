package com.dsm.coupon

import com.dsm.androidcomponent.ext.formatToIso
import com.dsm.coupon.viewModel.CouponIssueViewModel
import com.dsm.model.repository.CouponRepository
import com.dsm.testcomponent.BaseTest
import com.example.error.exception.ForbiddenException
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class CouponIssueViewModelTests : BaseTest() {

    @Mock
    private lateinit var couponRepository: CouponRepository

    private lateinit var viewModel: CouponIssueViewModel

    @Before
    fun init() {
        viewModel = CouponIssueViewModel(couponRepository)
    }

    @Test
    fun isIssueEnableTest() {
        viewModel.run {
            setExpireDate(1000, 10, 1)

            isIssueEnable.test().assertValue(false)

            price.value = "1000"

            isIssueEnable.test().assertValue(true)
        }
    }

    @Test
    fun onCouponIssueForbiddenTest() = runBlockingTest {
        viewModel.run {
            setExpireDate(2000, 1, 1)
            price.value = "1000"
            `when`(couponRepository.issueCoupon(formatToIso(expireDate.value!!), price.value!!.toInt()))
                .thenThrow(ForbiddenException(Exception()))

            onClickIssueCoupon()

            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
        }
    }

    @Test
    fun onCouponIssueSuccessTest() = runBlockingTest {
        viewModel.run {
            setExpireDate(2000, 1, 1)
            price.value = "1000"
            `when`(couponRepository.issueCoupon(formatToIso(expireDate.value!!), price.value!!.toInt()))
                .thenReturn(Unit)

            onClickIssueCoupon()

            toastEvent.test().assertNoValue()
        }
    }
}