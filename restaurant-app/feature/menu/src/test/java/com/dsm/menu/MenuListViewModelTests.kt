package com.dsm.menu

import androidx.lifecycle.liveData
import com.dsm.menu.viewModel.MenuViewModel
import com.dsm.model.Menu
import com.dsm.model.MenuCategory
import com.dsm.model.MenuGroupItem
import com.dsm.model.MenuOptionItem
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
                MenuGroupItem(
                    groupId = 0,
                    name = "name",
                    maxCount = 2,
                    option = listOf(
                        MenuOptionItem(
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
}