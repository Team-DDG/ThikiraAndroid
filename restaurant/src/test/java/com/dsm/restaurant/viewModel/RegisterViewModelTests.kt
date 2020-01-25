package com.dsm.restaurant.viewModel

import com.dsm.restaurant.BaseTest
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ConflictException
import com.dsm.restaurant.data.error.exception.InternalException
import com.dsm.restaurant.data.firebase.FirebaseSource
import com.dsm.restaurant.domain.interactor.CheckEmailUseCase
import com.dsm.restaurant.domain.interactor.SearchAddressUseCase
import com.dsm.restaurant.domain.model.AddressModel
import com.dsm.restaurant.presentation.ui.register.RegisterViewModel
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
class RegisterViewModelTests : BaseTest() {

    @Mock
    private lateinit var checkEmailUseCase: CheckEmailUseCase

    @Mock
    private lateinit var searchAddressUseCase: SearchAddressUseCase

    @Mock
    private lateinit var firebaseSource: FirebaseSource

    @Captor
    private lateinit var uploadListener: ArgumentCaptor<FirebaseSource.UploadListener>

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun init() {
        viewModel = RegisterViewModel(checkEmailUseCase, searchAddressUseCase, firebaseSource)
    }

    /**
     * 버튼 활성화 체크
     */
    @Test
    fun isNext1EnabledTest() {
        viewModel.uploadImage("imagepath")
        verify(firebaseSource).uploadImage(safeEq("imagepath"), capture(uploadListener))
        uploadListener.value.onSuccess("imageurl")

        viewModel.onSelectRestaurantAddress("ADDRESS", "ROAD_ADDRESS")

        viewModel.run {
            isNext1Enabled.test().assertValue(false)

            restaurantName.value = "RESTAURANT_NAME"
            phoneNum.value = "PHONE_NUM"
            area.value = listOf("AREA")
            email.value = "example@naver.com"

            isNext1Enabled.test().assertValue(true)

            restaurantName.value = ""

            isNext1Enabled.test().assertValue(false)
        }
    }

    /**
     * 파이어베이스 이미지 업로드
     */
    @Test
    fun imageUploadSuccessTest() {
        viewModel.uploadImage("imagepath")
        viewModel.isUploadingImage.test().assertValue(true)

        verify(firebaseSource).uploadImage(safeEq("imagepath"), capture(uploadListener))

        uploadListener.value.onSuccess("imageurl")
        viewModel.imageUrl.test().assertValue("imageurl")

        uploadListener.value.onComplete()
        viewModel.isUploadingImage.test().assertValue(false)
    }

    @Test
    fun imageUploadFailedTest() {
        viewModel.uploadImage("imagepath")
        viewModel.isUploadingImage.test().assertValue(true)

        verify(firebaseSource).uploadImage(safeEq("imagepath"), capture(uploadListener))

        uploadListener.value.onFailure(Exception())
        viewModel.toastEvent.test().assertValue(R.string.fail_image_uploading)

        uploadListener.value.onComplete()
        viewModel.isUploadingImage.test().assertValue(false)
    }

    /**
     * 주소 검색 및 선택
     */
    @Test
    fun isAddressSearchableTest() {
        viewModel.run {
            addressSearch.value = ""
            isAddressSearchable.test().assertValue(false)

            addressSearch.value = "대덕소프트웨어"
            isAddressSearchable.test().assertValue(true)
        }
    }

    @Test
    fun searchAddressSuccessTest() = runBlockingTest {
        viewModel.run {
            addressSearch.value = "SEARCH"

            val result = listOf(AddressModel("title", "address", "roadAddress"))
            `when`(searchAddressUseCase.invoke(addressSearch.value!!))
                .thenReturn(result)

            onClickSearchAddress()

            verify(searchAddressUseCase).invoke("SEARCH")
            addressList.test().assertValue(result)
            isSearchingAddress.test().assertValue(false)
        }
    }

    @Test
    fun searchAddressFailedTest() = runBlockingTest {
        viewModel.run {
            addressSearch.value = "SEARCH"
            `when`(searchAddressUseCase.invoke(addressSearch.value!!))
                .thenThrow(InternalException(Exception()))

            onClickSearchAddress()

            isSearchingAddress.test().assertValue(false)
            toastEvent.test().assertValue(R.string.fail_internal)
        }
    }

    /**
     * 이메일 검증 및 중복 검사
     */
    @Test
    fun blankEmailTest() {
        viewModel.run {
            email.value = ""

            checkEmail()

            snackbarEvent.test().assertValue(R.string.fail_blank_email)
        }
    }

    @Test
    fun invalidEmailTest() {
        viewModel.run {
            email.value = "test@naver."

            checkEmail()

            snackbarEvent.test().assertValue(R.string.fail_invalid_email)
        }
    }

    @Test
    fun checkEmailSuccessTest() = runBlockingTest {
        viewModel.run {
            email.value = "test@naver.com"

            `when`(checkEmailUseCase.invoke(email.value!!)).thenReturn(Unit)

            checkEmail()

            toastEvent.test().assertValue(R.string.success_email_check)
        }
    }

    @Test
    fun emailConflictTest() = runBlockingTest {
        viewModel.run {
            email.value = "test@naver.com"

            `when`(checkEmailUseCase.invoke(email.value!!))
                .thenThrow(ConflictException(Exception()))

            checkEmail()

            toastEvent.test().assertValue(R.string.fail_conflict_email)
        }
    }
}