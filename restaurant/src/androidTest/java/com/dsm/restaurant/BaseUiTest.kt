package com.dsm.restaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.IdlingRegistry
import com.dsm.restaurant.presentation.util.EspressoIdlingResource
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.nio.charset.Charset

open class BaseUiTest {

    companion object {
        private const val MOCK_SERVER_PORT = 1313
    }

    private val mockServer = MockWebServer()

    @Mock
    protected lateinit var mockNavController: NavController

    @Before
    fun mock() {
        MockitoAnnotations.initMocks(this)
    }

    @Before
    fun startServer() {
        mockServer.start(MOCK_SERVER_PORT)
    }

    @After
    fun shutdownServer() {
        mockServer.shutdown()
    }

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    protected fun setDispatcher(dispatcher: Dispatcher) {
        mockServer.dispatcher = dispatcher
    }

    protected fun parseJsonFile(fileName: String): String {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val buffer = ByteArray(inputStream.available())
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charset.forName("UTF-8"))
    }

    protected fun <F : Fragment> launchFragment(clazz: Class<F>, bundle: Bundle? = null, style: Int = R.style.AppTheme): FragmentScenario<F> {
        val scenario = FragmentScenario.launchInContainer(
            clazz, bundle, style, null
        )
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }
        return scenario
    }

    protected fun getErrorDispatcher(errorCode: Int, body: String = "") = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse =
            MockResponse().setResponseCode(errorCode).setBody(body)

    }
}