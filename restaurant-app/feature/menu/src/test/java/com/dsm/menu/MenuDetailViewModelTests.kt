package com.dsm.menu

import com.dsm.error.exception.ForbiddenException
import com.dsm.menu.viewModel.MenuDetailViewModel
import com.dsm.model.repository.MenuRepository
import com.dsm.testcomponent.BaseTest
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class MenuDetailViewModelTests : BaseTest() {

    @Mock
    private lateinit var menuRepository: MenuRepository

    private lateinit var viewModel: MenuDetailViewModel

    @Before
    fun init() {
        viewModel = MenuDetailViewModel(menuRepository)
    }

    @Test
    fun deleteMenuSuccessTest() = runBlockingTest {
        viewModel.run {
            `when`(menuRepository.deleteMenu(0)).thenReturn(Unit)

            viewModel.onClickDeleteMenu(0)

            verify(menuRepository).deleteMenu(0)
            popEvent.test().assertHasValue()
        }
    }

    @Test
    fun deleteMenuForbiddenTest() = runBlockingTest {
        viewModel.run {
            `when`(menuRepository.deleteMenu(0))
                .thenThrow(ForbiddenException(Exception()))

            viewModel.onClickDeleteMenu(0)

            verify(menuRepository).deleteMenu(0)
            toastEvent.test().assertHasValue()
        }
    }
}