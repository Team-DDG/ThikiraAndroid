package com.dsm.restaurant.repository

import com.dsm.restaurant.data.dataSource.MenuCategoryDataSource
import com.dsm.restaurant.data.dataSource.MenuDataSource
import com.dsm.restaurant.data.local.dto.GroupLocalDto
import com.dsm.restaurant.data.local.dto.MenuLocalDto
import com.dsm.restaurant.data.local.dto.OptionLocalDto
import com.dsm.restaurant.data.remote.dto.GroupDto
import com.dsm.restaurant.data.remote.dto.MenuDto
import com.dsm.restaurant.data.remote.dto.OptionDto
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
                GroupLocalDto(
                    groupId = 0,
                    name = "NAME",
                    maxCount = 2,
                    option = listOf(
                        OptionLocalDto(
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
                GroupDto(
                    groupId = 0,
                    name = "NAME",
                    maxCount = 2,
                    option = listOf(
                        OptionDto(
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
            `when`(menuCategoryDataSource.getLocalMenuCategoryIdByName("CATEGORY_NAME")).thenReturn(0)
            `when`(menuDataSource.getLocalMenuList(0)).thenReturn(localMenuListResponse)

            menuRepository.getMenuList("CATEGORY_NAME", false)

            verify(menuCategoryDataSource).getLocalMenuCategoryIdByName("CATEGORY_NAME")
            verify(menuDataSource).getLocalMenuList(0)

            verifyNoMoreInteractions(menuDataSource)
        }
    }

    @Test
    fun `when force update false, local menu list empty`() {
        runBlocking {
            `when`(menuCategoryDataSource.getLocalMenuCategoryIdByName("CATEGORY_NAME")).thenReturn(0)
            `when`(menuDataSource.getLocalMenuList(0)).thenReturn(emptyList())
            `when`(menuDataSource.getRemoteMenuList(0)).thenReturn(remoteMenuListResponse)

            menuRepository.getMenuList("CATEGORY_NAME", false)

            verify(menuCategoryDataSource).getLocalMenuCategoryIdByName("CATEGORY_NAME")
            verify(menuDataSource).getRemoteMenuList(0)
            verify(menuDataSource).deleteAllLocalMenu(0)
            verify(menuDataSource).insertLocalMenuList(remoteMenuListResponse.map { it.toLocalDto(0) })
        }
    }

    @Test
    fun `when force update true`() {
        runBlocking {
            `when`(menuCategoryDataSource.getLocalMenuCategoryIdByName("CATEGORY_NAME")).thenReturn(0)
            `when`(menuDataSource.getRemoteMenuList(0)).thenReturn(remoteMenuListResponse)

            menuRepository.getMenuList("CATEGORY_NAME", false)

            verify(menuDataSource).getRemoteMenuList(0)
            verify(menuDataSource).deleteAllLocalMenu(0)
            verify(menuDataSource).insertLocalMenuList(remoteMenuListResponse.map { it.toLocalDto(0) })
        }
    }
}