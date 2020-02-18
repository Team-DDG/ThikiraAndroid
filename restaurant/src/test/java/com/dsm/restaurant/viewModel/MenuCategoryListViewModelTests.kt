package com.dsm.restaurant.viewModel

import com.dsm.restaurant.BaseTest
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.domain.interactor.DeleteMenuCategoryListUseCase
import com.dsm.restaurant.domain.interactor.GetMenuCategoryListUseCase
import com.dsm.restaurant.domain.interactor.UpdateMenuCategoryUseCase
import com.dsm.restaurant.domain.model.MenuCategoryModel
import com.dsm.restaurant.presentation.ui.adapter.MenuCategoryListAdapter.Companion.NORMAL_TYPE
import com.dsm.restaurant.presentation.ui.menu.category.MenuCategoryListViewModel
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

    @Mock
    private lateinit var updateMenuCategoryUseCase: UpdateMenuCategoryUseCase

    private lateinit var viewModel: MenuCategoryListViewModel

    @Before
    fun init() {
        viewModel = MenuCategoryListViewModel(getMenuCategoryListUseCase, deleteMenuCategoryListUseCase, updateMenuCategoryUseCase)
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

            onClickDeleteCheckbox(0)
            onClickDeleteCheckbox(1)
            onClickDeleteCheckbox(2)

            onClickDelete()

            changeViewTypeEvent.test().assertValue(NORMAL_TYPE)
            isSelecting.test().assertValue(false)
            toastEvent.test().assertNoValue()
        }
    }

    @Test
    fun deleteMenuCategoryListForbiddenTest() = runBlockingTest {
        viewModel.run {
            `when`(deleteMenuCategoryListUseCase.invoke(listOf(0, 1, 2)))
                .thenThrow(ForbiddenException(Exception()))

            onClickDeleteCheckbox(0)
            onClickDeleteCheckbox(1)
            onClickDeleteCheckbox(2)

            onClickDelete()

            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
        }
    }

    @Test
    fun updateMenuCategorySuccessTest() = runBlockingTest {
        viewModel.run {
            `when`(updateMenuCategoryUseCase.invoke(0, "NAME")).thenReturn(Unit)

            onClickUpdate("NAME", 0)

            toastEvent.test().assertNoValue()
        }
    }

    @Test
    fun updateMenuCategoryForbiddenTest() = runBlockingTest {
        viewModel.run {
            `when`(updateMenuCategoryUseCase.invoke(0, "NAME"))
                .thenThrow(ForbiddenException(Exception()))

            onClickUpdate("NAME", 0)

            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
        }
    }
}