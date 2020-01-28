package com.dsm.restaurant.viewModel

import com.dsm.restaurant.BaseTest
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ConflictException
import com.dsm.restaurant.data.error.exception.InternalException
import com.dsm.restaurant.data.firebase.FirebaseSource
import com.dsm.restaurant.domain.interactor.CheckEmailUseCase
import com.dsm.restaurant.domain.interactor.RegisterUseCase
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
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalCoroutinesApi
class RegisterViewModelTests : BaseTest() {

    @Mock
    private lateinit var checkEmailUseCase: CheckEmailUseCase

    @Mock
    private lateinit var searchAddressUseCase: SearchAddressUseCase

    @Mock
    private lateinit var registerUseCase: RegisterUseCase

    @Mock
    private lateinit var firebaseSource: FirebaseSource

    @Captor
    private lateinit var uploadListener: ArgumentCaptor<FirebaseSource.UploadListener>

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun init() {
        viewModel = RegisterViewModel(checkEmailUseCase, searchAddressUseCase, registerUseCase, firebaseSource)
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

    @Test
    fun isNext2EnabledTest() {
        viewModel.run {
            onSelectCategory("CATEGORY")
            minPrice.value = "MIN_PRICE"
            dayOff.value = "DAY_OFF"
            startHour.value = 1
            startMinute.value = 0
            endHour.value = 2
            endMinute.value = 0

            isNext2Enabled.test().assertValue(true)

            endHour.value = 1

            isNext2Enabled.test().assertValue(false)
        }
    }

    @Test
    fun isNext3EnabledTest() {
        viewModel.run {
            description.value = ""
            isNext3Enabled.test().assertValue(false)

            description.value = "DESCRIPTION"
            isNext3Enabled.test().assertValue(true)
        }
    }

    @Test
    fun isRegisterEnabledTest() {
        viewModel.run {
            email.value = "EMAIL"
            password.value = "PASSWORD"
            reTypePwd.value = "PASSWORD"

            isRegisterEnabled.test().assertValue(true)

            email.value = ""

            isRegisterEnabled.test().assertValue(false)
        }
    }

    /**
     * 비밀번호 유효성 및 재입력 검사
     */
    @Test
    fun passwordValidationTest() {
        viewModel.run {
            password.value = "dfdfd"
            isPasswordValid.test().assertValue(false)

            password.value = "dfdfdf@"
            isPasswordValid.test().assertValue(true)
        }
    }

    @Test
    fun passwordReTypeTest() {
        viewModel.run {
            password.value = "PASSWORD"
            reTypePwd.value = "PASSWORD_RETYPE"

            isPasswordReTypeCorrect.test().assertValue(false)

            reTypePwd.value = "PASSWORD"

            isPasswordReTypeCorrect.test().assertValue(true)
        }
    }

    /**
     * 영업시간 유효성 확인
     */
    @Test
    fun endTimeFasterTest() {
        viewModel.run {
            startHour.value = 9
            startMinute.value = 0
            endHour.value = 5
            endMinute.value = 0

            isStoreHoursValid.test().assertValue(false)
        }
    }

    @Test
    fun startTimeFasterTest() {
        viewModel.run {
            startHour.value = 9
            startMinute.value = 0
            endHour.value = 10
            endMinute.value = 0

            isStoreHoursValid.test().assertValue(true)
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

    /**
     * 업체 등록
     */
    @Test
    fun registerSusccessTest() = runBlockingTest {
        viewModel.run {
            restaurantName.value = "RESTAURANT_NAME"
            phoneNum.value = "PHONE_NUM"
            area.value = listOf("AREA")
            minPrice.value = "MIN_PRICE"
            dayOff.value = "DAY_OFF"
            startHour.value = 0
            startMinute.value = 0
            endHour.value = 1
            endMinute.value = 1
            description.value = "DESCRIPTION"
            email.value = "EMAIL"
            password.value = "PASSWORD"
            reTypePwd.value = "RETYPE"

            setIsOfflineEnable(true)
            setIsOnlineEnable(true)

            onSelectRestaurantAddress("ADDRESS", "ROAD_ADDRESS")

            viewModel.uploadImage("imagepath")
            verify(firebaseSource).uploadImage(safeEq("imagepath"), capture(uploadListener))
            uploadListener.value.onSuccess("imageurl")

            val startTime = parseTimeToDate(startHour.value!!, startMinute.value!!)
            val endTime = parseTimeToDate(endHour.value!!, endMinute.value!!)

            `when`(
                registerUseCase.invoke(
                    hashMapOf(
                        "image" to imageUrl.value,
                        "name" to restaurantName.value,
                        "phone" to phoneNum.value,
                        "add_street" to roadAddress.value,
                        "add_parcel" to address.value,
                        "area" to area.value,
                        "category" to category.value,
                        "min_price" to minPrice.value,
                        "day_off" to dayOff.value,
                        "online_payment" to isOnlineEnable.value,
                        "offline_payment" to isOfflineEnable.value,
                        "open_time" to timeToString(startTime),
                        "close_time" to timeToString(endTime),
                        "description" to description.value,
                        "email" to email.value,
                        "password" to password.value
                    )
                )
            ).thenReturn(Unit)

            register()

            toastEvent.test().assertValue(R.string.success_register_restaurant)
            popToLoginEvent.test().assertHasValue()
        }
    }

    @Test
    fun registerFailedTest() = runBlockingTest {
        viewModel.run {
            restaurantName.value = "RESTAURANT_NAME"
            phoneNum.value = "PHONE_NUM"
            area.value = listOf("AREA")
            minPrice.value = "MIN_PRICE"
            dayOff.value = "DAY_OFF"
            startHour.value = 0
            startMinute.value = 0
            endHour.value = 1
            endMinute.value = 1
            description.value = "DESCRIPTION"
            email.value = "EMAIL"
            password.value = "PASSWORD"
            reTypePwd.value = "RETYPE"

            setIsOfflineEnable(true)
            setIsOnlineEnable(true)

            onSelectRestaurantAddress("ADDRESS", "ROAD_ADDRESS")

            viewModel.uploadImage("imagepath")
            verify(firebaseSource).uploadImage(safeEq("imagepath"), capture(uploadListener))
            uploadListener.value.onSuccess("imageurl")

            val startTime = parseTimeToDate(startHour.value!!, startMinute.value!!)
            val endTime = parseTimeToDate(endHour.value!!, endMinute.value!!)

            `when`(
                registerUseCase.invoke(
                    hashMapOf(
                        "image" to imageUrl.value,
                        "name" to restaurantName.value,
                        "phone" to phoneNum.value,
                        "add_street" to roadAddress.value,
                        "add_parcel" to address.value,
                        "area" to area.value,
                        "category" to category.value,
                        "min_price" to minPrice.value,
                        "day_off" to dayOff.value,
                        "online_payment" to isOnlineEnable.value,
                        "offline_payment" to isOfflineEnable.value,
                        "open_time" to timeToString(startTime),
                        "close_time" to timeToString(endTime),
                        "description" to description.value,
                        "email" to email.value,
                        "password" to password.value
                    )
                )
            ).thenThrow(InternalException(Exception()))

            register()

            toastEvent.test().assertValue(R.string.fail_internal)
        }
    }

    private fun parseTimeToDate(hour: Int, minute: Int): Date {
        val parser = SimpleDateFormat("HH:mm", Locale.KOREA)
        return parser.parse("$hour:$minute")!!
    }

    private fun timeToString(time: Date): String {
        val formatter = SimpleDateFormat("HH:mm", Locale.KOREA)
        return formatter.format(time)
    }
}