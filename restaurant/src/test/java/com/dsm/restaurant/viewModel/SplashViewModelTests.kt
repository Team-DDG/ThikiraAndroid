package com.dsm.restaurant.viewModel

import com.dsm.restaurant.BaseTest
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.domain.interactor.AuthTokenUseCase
import com.dsm.restaurant.presentation.ui.splash.SplashViewModel
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class SplashViewModelTests : BaseTest() {

    @Mock
    private lateinit var authTokenUseCase: AuthTokenUseCase

    private lateinit var viewModel: SplashViewModel

    @Before
    fun init() {
        viewModel = SplashViewModel(authTokenUseCase)
    }

    @Test
    fun authSuccessTest() = runBlockingTest {
        viewModel.run {
            `when`(authTokenUseCase.invoke()).thenReturn(Unit)

            authToken()

            navigateMainEvent.test().assertHasValue()
        }
    }

    @Test
    fun authForbiddenTest() = runBlockingTest {
        viewModel.run {
            `when`(authTokenUseCase.invoke()).thenThrow(ForbiddenException(Exception()))

            authToken()

            navigateLoginEvent.test().assertHasValue()
        }
    }
}