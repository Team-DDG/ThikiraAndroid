package com.dsm.restaurant.repository

import com.dsm.restaurant.data.dataSource.MenuCategoryDataSource
import com.dsm.restaurant.data.dataSource.MenuDataSource
import com.dsm.restaurant.data.local.dto.GroupLocalItem
import com.dsm.restaurant.data.local.dto.MenuLocalDto
import com.dsm.restaurant.data.local.dto.OptionLocalItem
import com.dsm.restaurant.data.remote.dto.GroupItem
import com.dsm.restaurant.data.remote.dto.MenuDto
import com.dsm.restaurant.data.remote.dto.OptionItem
import com.dsm.restaurant.data.repository.MenuRepositoryImpl
import com.dsm.restaurant.domain.repository.MenuRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MenuRepositoryTests {

    @Mock
    private lateinit var menuDataSource: MenuDataSource

    @Mock
    private lateinit var menuCategoryDataSource: MenuCategoryDataSource

    private lateinit var menuRepository: MenuRepository

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        menuRepository = MenuRepositoryImpl(menuDataSource, menuCategoryDataSource)
    }

    private val localMenuListResponse = listOf(
        MenuLocalDto(
            menuId = 0,
            name = "NAME",
            price = 1000,
            image = "IMAGE",
            description = "DESCRIPTION",
            menuCategoryId = 0,
            group = listOf(
                GroupLocalItem(
                    groupId = 0,
                    name = "NAME",
                    maxCount = 2,
                    option = listOf(
                        OptionLocalItem(
                            optionId = 0,
                            name = "NAME",
                            price = 1000
                        )
                    )
                )
            )
        )
    )

    private val remoteMenuListResponse = listOf(
        MenuDto(
            menuId = 0,
            name = "NAME",
            price = 1000,
            image = "IMAGE",
            description = "DESCRIPTION",
            group = listOf(
                GroupItem(
                    groupId = 0,
                    name = "NAME",
                    maxCount = 2,
                    option = listOf(
                        OptionItem(
                            optionId = 0,
                            name = "NAME",
                            price = 1000
                        )
                    )
                )
            )
        )
    )

    @Test
    fun `when force update false, local menu list not empty`() {
        runBlocking {
            `when`(menuCategoryDataSource.getMenuCategoryIdFromName("CATEGORY_NAME")).thenReturn(0)
            `when`(menuDataSource.getLocalMenuList(0)).thenReturn(localMenuListResponse)

            menuRepository.getMenuList("CATEGORY_NAME", false)

            verify(menuCategoryDataSource).getMenuCategoryIdFromName("CATEGORY_NAME")
            verify(menuDataSource).getLocalMenuList(0)

            verifyNoMoreInteractions(menuDataSource)
        }
    }

    @Test
    fun `when force update false, local menu list empty`() {
        runBlocking {
            `when`(menuCategoryDataSource.getMenuCategoryIdFromName("CATEGORY_NAME")).thenReturn(0)
            `when`(menuDataSource.getLocalMenuList(0)).thenReturn(emptyList())
            `when`(menuDataSource.getRemoteMenuList(0)).thenReturn(remoteMenuListResponse)

            menuRepository.getMenuList("CATEGORY_NAME", false)

            verify(menuCategoryDataSource).getMenuCategoryIdFromName("CATEGORY_NAME")
            verify(menuDataSource).getRemoteMenuList(0)
            verify(menuDataSource).deleteAllLocalMenu(0)
            verify(menuDataSource).insertLocalMenuList(remoteMenuListResponse.map { it.toLocalDto(0) })
        }
    }

    @Test
    fun `when force update true`() {
        runBlocking {
            `when`(menuCategoryDataSource.getMenuCategoryIdFromName("CATEGORY_NAME")).thenReturn(0)
            `when`(menuDataSource.getRemoteMenuList(0)).thenReturn(remoteMenuListResponse)

            menuRepository.getMenuList("CATEGORY_NAME", false)

            verify(menuDataSource).getRemoteMenuList(0)
            verify(menuDataSource).deleteAllLocalMenu(0)
            verify(menuDataSource).insertLocalMenuList(remoteMenuListResponse.map { it.toLocalDto(0) })
        }
    }
}