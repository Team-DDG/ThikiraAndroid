package com.dsm.restaurant.viewModel

import com.dsm.restaurant.BaseTest
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.domain.interactor.GetMenuCategoryListUseCase
import com.dsm.restaurant.domain.interactor.GetMenuListUseCase
import com.dsm.restaurant.domain.model.GroupModel
import com.dsm.restaurant.domain.model.MenuCategoryModel
import com.dsm.restaurant.domain.model.MenuModel
import com.dsm.restaurant.domain.model.OptionModel
import com.dsm.restaurant.presentation.ui.menu.list.MenuListViewModel
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class MenuListViewModelTests : BaseTest() {

    @Mock
    private lateinit var getMenuCategoryListUseCase: GetMenuCategoryListUseCase

    @Mock
    private lateinit var getMenuListUseCase: GetMenuListUseCase

    private lateinit var viewModel: MenuListViewModel

    @Before
    fun init() {
        viewModel = MenuListViewModel(getMenuCategoryListUseCase, getMenuListUseCase)
    }

    val menuListResponse = listOf(
        MenuModel(
            menuId = 0,
            description = "description",
            image = "image",
            name = "name",
            price = 1000,
            group = listOf(
                GroupModel(
                    groupId = 0,
                    name = "name",
                    maxCount = 2,
                    option = listOf(
                        OptionModel(
                            optionId = 0,
                            name = "name",
                            price = 1000
                        )
                    )
                )
            )
        )
    )

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

            menuCategoryList.test().assertValue(response.map { it.name })
            toastEvent.test().assertNoValue()
        }
    }

    @Test
    fun getMenuCategoryFailedTest() = runBlockingTest {
        viewModel.run {
            `when`(getMenuCategoryListUseCase.invoke(true))
                .thenThrow(ForbiddenException(Exception()))

            getMenuCategory(true)

            menuCategoryList.test().assertValue(listOf(""))
            toastEvent.test().assertNoValue()
        }
    }

    @Test
    fun getMenuListSuccessTest() = runBlockingTest {
        viewModel.run {
            `when`(getMenuListUseCase.invoke("CATEGORY_NAME", true)).thenReturn(menuListResponse)

            getMenuList("CATEGORY_NAME", true)

            menuList.test().assertValue(menuListResponse)
            toastEvent.test().assertNoValue()
        }
    }

    @Test
    fun getMenuListForbiddenTest() = runBlockingTest {
        viewModel.run {
            `when`(getMenuListUseCase.invoke("CATEGORY_NAME", true))
                .thenThrow(ForbiddenException(Exception()))

            getMenuList("CATEGORY_NAME", true)

            menuList.test().assertNoValue()
            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
        }
    }
}