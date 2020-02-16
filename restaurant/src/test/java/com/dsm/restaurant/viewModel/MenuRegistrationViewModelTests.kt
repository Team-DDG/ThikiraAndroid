package com.dsm.restaurant.viewModel

import com.dsm.restaurant.BaseTest
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.data.firebase.FirebaseSource
import com.dsm.restaurant.domain.interactor.UploadMenuUseCase
import com.dsm.restaurant.domain.model.MenuCategoryModel
import com.dsm.restaurant.presentation.ui.main.menu.registration.MenuRegistrationViewModel
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class MenuRegistrationViewModelTests : BaseTest() {

    @Mock
    private lateinit var uploadMenuUseCase: UploadMenuUseCase

    @Mock
    private lateinit var firebaseSource: FirebaseSource

    private lateinit var viewModel: MenuRegistrationViewModel

    @Captor
    private lateinit var uploadListener: ArgumentCaptor<FirebaseSource.UploadListener>

    @Before
    fun init() {
        viewModel = MenuRegistrationViewModel(uploadMenuUseCase, firebaseSource)
    }

    @Test
    fun uploadMenuSuccessTest() = runBlockingTest {
        viewModel.run {
            uploadImage("IMAGE_PATH")
            verify(firebaseSource).uploadImage(safeEq("IMAGE_PATH"), capture(uploadListener))
            uploadListener.value.onSuccess("IMAGE_URL")
            setMenuCategory(MenuCategoryModel(0, "CATEGORY_NAME"))
            name.value = "NAME"
            price.value = "100"
            description.value = "DESCRIPTION"
            addGroup("GROUP_NAME", 2)

            `when`(
                uploadMenuUseCase.invoke(
                    hashMapOf(
                        "mc_id" to menuCategoryId.value,
                        "name" to name.value,
                        "price" to price.value?.toInt(),
                        "description" to description.value,
                        "image" to imageUrl.value,
                        "group" to groupOptionList.value
                    )
                )
            ).thenReturn(Unit)

            uploadMenu()

            finishActivityEvent.test().assertHasValue()
        }
    }

    @Test
    fun uploadMenuForbiddenTest() = runBlockingTest {
        viewModel.run {
            uploadImage("IMAGE_PATH")
            verify(firebaseSource).uploadImage(safeEq("IMAGE_PATH"), capture(uploadListener))
            uploadListener.value.onSuccess("IMAGE_URL")
            setMenuCategory(MenuCategoryModel(0, "CATEGORY_NAME"))
            name.value = "NAME"
            price.value = "100"
            description.value = "DESCRIPTION"
            addGroup("GROUP_NAME", 2)

            `when`(
                uploadMenuUseCase.invoke(
                    hashMapOf(
                        "mc_id" to menuCategoryId.value,
                        "name" to name.value,
                        "price" to price.value?.toInt(),
                        "description" to description.value,
                        "image" to imageUrl.value,
                        "group" to groupOptionList.value
                    )
                )
            ).thenThrow(ForbiddenException(Exception()))

            uploadMenu()

            toastEvent.test().assertValue(R.string.fail_exception_forbidden)
        }
    }
}