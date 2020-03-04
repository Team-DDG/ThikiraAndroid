package com.dsm.menu

import androidx.lifecycle.liveData
import com.dsm.error.exception.ForbiddenException
import com.dsm.menu.viewModel.MenuCategoryViewModel
import com.dsm.model.MenuCategory
import com.dsm.model.repository.MenuCategoryRepository
import com.dsm.testcomponent.BaseTest
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class MenuCategoryListViewModelTests : BaseTest() {

    @Mock
    private lateinit var menuCategoryRepository: MenuCategoryRepository

    private lateinit var viewModel: MenuCategoryViewModel

    @Before
    fun init() {
        viewModel = MenuCategoryViewModel(menuCategoryRepository)
    }

    @Test
    fun observeMenuCategorySuccessTest() = runBlockingTest {
        viewModel.run {
            val response = listOf(MenuCategory(0, "NAME"))
            `when`(menuCategoryRepository.observeMenuCategories()).thenReturn(liveData { emit(response) })

            loadMenuCategories(true)

            menuCategories.test().assertValue(response)
        }
    }

    @Test
    fun updateMenuCategoryForbiddenTest() = runBlockingTest {
        viewModel.run {
            `when`(menuCategoryRepository.updateMenuCategoryName(0, "NAME"))
                .thenThrow(ForbiddenException(Exception()))

            onClickUpdate(0, "NAME")

            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
        }
    }
}