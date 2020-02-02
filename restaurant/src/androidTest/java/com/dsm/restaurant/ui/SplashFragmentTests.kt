package com.dsm.restaurant.ui

import android.net.Uri
import com.dsm.restaurant.BaseUiTest
import com.dsm.restaurant.R
import com.dsm.restaurant.presentation.ui.splash.SplashFragment
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import org.junit.Test
import org.mockito.Mockito.verify

class SplashFragmentTests : BaseUiTest() {

    @Test
    fun authTokenSuccessTest() {
        setDispatcher(getDispatcher())

        launchFragment(SplashFragment::class.java)

        Thread.sleep(1000)

        verify(mockNavController).navigate(R.id.action_splashFragment_to_mainFragment)
    }

    @Test
    fun authTokenForbiddenTest() {
        setDispatcher(getErrorDispatcher(409))

        launchFragment(SplashFragment::class.java)

        Thread.sleep(1000)

        verify(mockNavController).navigate(R.id.action_splashFragment_to_loginFragment)
    }

    private fun getDispatcher() = object: Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse =
            when (Uri.parse(request.path).path) {
                "/auth" -> MockResponse().setResponseCode(200)
                else -> MockResponse().setResponseCode(404)
            }

    }
}