package com.dsm.restaurant.viewModel

import com.dsm.restaurant.BaseTest
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.domain.interactor.DeleteMenuCategoryListUseCase
import com.dsm.restaurant.domain.interactor.GetMenuCategoryListUseCase
import com.dsm.restaurant.domain.model.MenuCategoryModel
import com.dsm.restaurant.presentation.ui.main.menu.category.MenuCategoryListViewModel
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
    private lateinit var getMenuCategoryListUseCase: GetMenuCategoryListUseCase

    @Mock
    private lateinit var deleteMenuCategoryListUseCase: DeleteMenuCategoryListUseCase

    private lateinit var viewModel: MenuCategoryListViewModel

    @Before
    fun init() {
        viewModel = MenuCategoryListViewModel(getMenuCategoryListUseCase, deleteMenuCategoryListUseCase)
    }

    @Test
    fun getMenuCategorySuccessTest() = runBlockingTest {
        viewModel.run {
            val response = listOf(
                MenuCategoryModel(
                    menuCategoryId = 0,
                    name = "NAME"
                )
            )
            `when`(getMenuCategoryListUseCase.invoke(true)).thenReturn(response)

            getMenuCategory(true)

            menuCategoryList.test().assertValue(response)
            toastEvent.test().assertNoValue()
        }
    }

    @Test
    fun getMenuCategoryForbiddenTest() = runBlockingTest {
        viewModel.run {
            `when`(getMenuCategoryListUseCase.invoke(true))
                .thenThrow(ForbiddenException(Exception()))

            getMenuCategory(true)

            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
        }
    }

    @Test
    fun deleteMenuCategoryListSuccessTest() = runBlockingTest {
        viewModel.run {
            `when`(deleteMenuCategoryListUseCase.invoke(listOf(0, 1, 2))).thenReturn(Unit)

            onDeleteCheck(0)
            onDeleteCheck(1)
            onDeleteCheck(2)

            onClickDelete()

            changeViewTypeNormalEvent.test().assertHasValue()
            isSelecting.test().assertValue(false)
            toastEvent.test().assertNoValue()
        }
    }

    @Test
    fun deleteMenuCategoryListForbiddenTest() = runBlockingTest {
        viewModel.run {
            `when`(deleteMenuCategoryListUseCase.invoke(listOf(0, 1, 2)))
                .thenThrow(ForbiddenException(Exception()))

            onDeleteCheck(0)
            onDeleteCheck(1)
            onDeleteCheck(2)

            onClickDelete()

            changeViewTypeNormalEvent.test().assertNoValue()
            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
        }
    }
}