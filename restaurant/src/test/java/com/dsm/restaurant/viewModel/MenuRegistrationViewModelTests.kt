package com.dsm.restaurant.viewModel

import com.dsm.restaurant.BaseTest
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.data.firebase.FirebaseStorageSource
import com.dsm.restaurant.domain.interactor.UploadMenuUseCase
import com.dsm.restaurant.presentation.model.MenuCategoryModel
import com.dsm.restaurant.presentation.model.MenuRegistrationModel
import com.dsm.restaurant.presentation.ui.menu.registration.MenuRegistrationViewModel
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
    private lateinit var uploadMenuUseCase: UploadMenuUseCase

    @Mock
    private lateinit var firebaseStorageSource: FirebaseStorageSource

    private lateinit var viewModel: MenuRegistrationViewModel

    @Before
    fun init() {
        viewModel = MenuRegistrationViewModel(uploadMenuUseCase, firebaseStorageSource)
    }

    @Test
    fun uploadMenuSuccessTest() = runBlockingTest {
        viewModel.run {
            uploadImage("IMAGE_PATH")
            setMenuCategory(MenuCategoryModel(0, "CATEGORY_NAME"))
            name.value = "NAME"
            price.value = "100"
            description.value = "DESCRIPTION"
            onClickAddGroup("GROUP_NAME", 2)

            `when`(
                uploadMenuUseCase.invoke(
                    MenuRegistrationModel(
                        menuCategoryId = menuCategoryId.value!!,
                        name = name.value!!,
                        price = price.value!!.toInt(),
                        description = description.value!!,
                        image = imageUrl.value!!,
                        group = menuOptionList.value!!
                    ).toEntity()
                )
            ).thenReturn(Unit)

            onClickUploadMenu()

            finishActivityEvent.test().assertHasValue()
        }
    }

    @Test
    fun uploadMenuForbiddenTest() = runBlockingTest {
        viewModel.run {
            uploadImage("IMAGE_PATH")
            setMenuCategory(MenuCategoryModel(0, "CATEGORY_NAME"))
            name.value = "NAME"
            price.value = "100"
            description.value = "DESCRIPTION"
            onClickAddGroup("GROUP_NAME", 2)

            `when`(
                uploadMenuUseCase.invoke(
                    MenuRegistrationModel(
                        menuCategoryId = menuCategoryId.value!!,
                        name = name.value!!,
                        price = price.value!!.toInt(),
                        description = description.value!!,
                        image = imageUrl.value!!,
                        group = menuOptionList.value!!
                    ).toEntity()
                )
            ).thenThrow(ForbiddenException(Exception()))

            onClickUploadMenu()

            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
        }
    }
}