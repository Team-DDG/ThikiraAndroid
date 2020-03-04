package com.dsm.menu

import androidx.lifecycle.liveData
import com.dsm.error.exception.ForbiddenException
import com.dsm.menu.viewModel.MenuViewModel
import com.dsm.model.Group
import com.dsm.model.Menu
import com.dsm.model.MenuCategory
import com.dsm.model.Option
import com.dsm.model.repository.MenuCategoryRepository
import com.dsm.model.repository.MenuRepository
import com.dsm.testcomponent.BaseTest
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
    private lateinit var menuRepository: MenuRepository

    @Mock
    private lateinit var menuCategoryRepository: MenuCategoryRepository

    private lateinit var viewModel: MenuViewModel

    @Before
    fun init() {
        viewModel = MenuViewModel(menuCategoryRepository, menuRepository)
    }

    val menuListResponse = listOf(
        Menu(
            menuId = 0,
            description = "description",
            image = "image",
            name = "name",
            price = 1000,
            group = listOf(
                Group(
                    groupId = 0,
                    name = "name",
                    maxCount = 2,
                    option = listOf(
                        Option(
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
            val response = listOf(MenuCategory(0, "NAME"))
            `when`(menuCategoryRepository.observeMenuCategories()).thenReturn(liveData { emit(response) })

            menuCategories.test().assertValue(response)
            toastEvent.test().assertNoValue()
        }
    }

    @Test
    fun getMenuCategoriesForbiddenTest() = runBlockingTest {
        viewModel.run {
            `when`(menuCategoryRepository.observeMenuCategories())
                .thenThrow(ForbiddenException(Exception()))

            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
        }
    }
}