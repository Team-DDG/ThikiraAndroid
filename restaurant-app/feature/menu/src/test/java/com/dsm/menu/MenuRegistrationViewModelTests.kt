package com.dsm.menu

import com.dsm.error.exception.ForbiddenException
import com.dsm.firebase.FirebaseStorageSource
import com.dsm.menu.viewModel.MenuRegistrationViewModel
import com.dsm.model.MenuCategory
import com.dsm.model.MenuRegistration
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
class MenuRegistrationViewModelTests : BaseTest() {

    @Mock
    private lateinit var menuRepository: MenuRepository

    @Mock
    private lateinit var firebaseStorageSource: FirebaseStorageSource

    private lateinit var viewModel: MenuRegistrationViewModel

    @Before
    fun init() {
        viewModel = MenuRegistrationViewModel(menuRepository, firebaseStorageSource)
    }

    @Test
    fun uploadMenuSuccessTest() = runBlockingTest {
        viewModel.run {
            uploadImage("IMAGE_PATH")
            setMenuCategory(MenuCategory(0, "CATEGORY_NAME"))
            name.value = "NAME"
            price.value = "100"
            description.value = "DESCRIPTION"
            onClickAddGroup("GROUP_NAME", 2)

            `when`(
                menuRepository.uploadMenu(
                    MenuRegistration(
                        menuCategoryId = menuCategory.value?.menuCategoryId ?: 0,
                        name = name.value ?: "",
                        price = price.value!!.toInt(),
                        description = description.value ?: "",
                        image = imageUrl.value ?: "",
                        group = menuOptions.value!!
                    )
                )
            ).thenReturn(Unit)

            onClickUploadMenu()

            popToMainEvent.test().assertHasValue()
        }
    }

    @Test
    fun uploadMenuForbiddenTest() = runBlockingTest {
        viewModel.run {
            uploadImage("IMAGE_PATH")
            setMenuCategory(MenuCategory(0, "CATEGORY_NAME"))
            name.value = "NAME"
            price.value = "100"
            description.value = "DESCRIPTION"
            onClickAddGroup("GROUP_NAME", 2)

            `when`(
                menuRepository.uploadMenu(
                    MenuRegistration(
                        menuCategoryId = menuCategory.value?.menuCategoryId ?: 0,
                        name = name.value ?: "",
                        price = price.value!!.toInt(),
                        description = description.value ?: "",
                        image = imageUrl.value ?: "",
                        group = menuOptions.value!!
                    )
                )
            ).thenThrow(ForbiddenException(Exception()))

            onClickUploadMenu()

            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
        }
    }
}