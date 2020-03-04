package com.dsm.account

import com.dsm.account.viewModel.RegisterViewModel
import com.dsm.error.exception.ConflictException
import com.dsm.error.exception.InternalException
import com.dsm.firebase.FirebaseStorageSource
import com.dsm.model.Address
import com.dsm.model.repository.AccountRepository
import com.dsm.model.repository.AuthRepository
import com.dsm.testcomponent.BaseTest
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.text.SimpleDateFormat
import java.util.*

@ExperimentalCoroutinesApi
class RegisterViewModelTests : BaseTest() {

    @Mock
    private lateinit var authRepository: AuthRepository

    @Mock
    private lateinit var accountRepository: AccountRepository

    @Mock
    private lateinit var firebaseStorageSource: FirebaseStorageSource

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun init() {
        viewModel = RegisterViewModel(accountRepository, authRepository, firebaseStorageSource)
    }

    @Test
    fun isNext2EnableTest() {
        viewModel.run {
            setCategory("CATEGORY")
            minPrice.value = "MIN_PRICE"
            dayOff.value = "DAY_OFF"

            isNext2Enable.test().assertValue(true)

            setCategory("")

            isNext2Enable.test().assertValue(false)
        }
    }

    @Test
    fun isNext3EnableTest() {
        viewModel.run {
            description.value = ""
            isNext3Enable.test().assertValue(false)

            description.value = "DESCRIPTION"
            isNext3Enable.test().assertValue(true)
        }
    }

    @Test
    fun isRegisterEnableTest() = runBlockingTest {
        viewModel.run {
            email.value = "email@gmail.com"
            `when`(authRepository.confirmEmailDuplication(email.value!!)).thenReturn(Unit)
            onClickDuplicationCheck()
            password.value = "PASSWORD"
            passwordRetype.value = "PASSWORD"

            isRegisterEnable.test().assertValue(true)

            password.value = ""

            isRegisterEnable.test().assertValue(false)
        }
    }

    @Test
    fun blankEmailTest() {
        viewModel.run {
            email.value = ""

            onClickDuplicationCheck()

            toastEvent.test().assertValue(R.string.fail_email_blank)
        }
    }

    @Test
    fun invalidEmailTest() {
        viewModel.run {
            email.value = "test@naver."

            onClickDuplicationCheck()

            toastEvent.test().assertValue(R.string.fail_email_invalid)
        }
    }

    @Test
    fun checkEmailSuccessTest() = runBlockingTest {
        viewModel.run {
            email.value = "test@naver.com"

            `when`(authRepository.confirmEmailDuplication(email.value!!)).thenReturn(Unit)

            onClickDuplicationCheck()

            toastEvent.test().assertValue(R.string.success_email_duplication_check)
            duplicateCheckedEmail.test().assertValue("test@naver.com")
            email.test().assertValue("")
        }
    }

    @Test
    fun emailConflictTest() = runBlockingTest {
        viewModel.run {
            email.value = "test@naver.com"

            `when`(authRepository.confirmEmailDuplication(email.value!!))
                .thenThrow(ConflictException(Exception()))

            onClickDuplicationCheck()

            toastEvent.test().assertValue(R.string.fail_email_conflict)
        }
    }

    /**
     * 업체 등록
     */
    @Test
    fun registerSuccessTest() = runBlockingTest {
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
            password.value = "PASSWORD!"
            passwordRetype.value = "PASSWORD!"

            setIsOffline(true)
            setIsOnline(true)

            setAddress(Address("TITLE", "ADDRESS", "ROAD_ADDRESS"))

            viewModel.uploadImage("imagepath")

            val startTime = parseTimeToDate(startHour.value!!, startMinute.value!!)
            val endTime = parseTimeToDate(endHour.value!!, endMinute.value!!)

            `when`(
                accountRepository.register(
                    hashMapOf(
                        "image" to imageUrl.value,
                        "name" to restaurantName.value,
                        "phone" to phoneNum.value,
                        "add_street" to address.value!!.roadAddress,
                        "add_parcel" to address.value!!.address,
                        "area" to area.value,
                        "category" to category.value,
                        "min_price" to minPrice.value,
                        "day_off" to dayOff.value,
                        "online_payment" to isOnlineEnable.value,
                        "offline_payment" to isOfflineEnable.value,
                        "open_time" to startTime,
                        "close_time" to endTime,
                        "description" to description.value,
                        "email" to email.value,
                        "password" to password.value
                    )
                )
            ).thenReturn(Unit)

            onClickRegister()

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
            password.value = "PASSWORD!"
            passwordRetype.value = "PASSWORD!"

            setIsOffline(true)
            setIsOnline(true)

            setAddress(Address("TITLE", "ADDRESS", "ROAD_ADDRESS"))

            viewModel.uploadImage("imagepath")

            val startTime = formatDateToTime(parseTimeToDate(startHour.value!!, startMinute.value!!))
            val endTime = formatDateToTime(parseTimeToDate(endHour.value!!, endMinute.value!!))

            `when`(
                accountRepository.register(
                    hashMapOf(
                        "image" to imageUrl.value,
                        "name" to restaurantName.value,
                        "phone" to phoneNum.value,
                        "add_street" to address.value!!.roadAddress,
                        "add_parcel" to address.value!!.address,
                        "area" to area.value,
                        "category" to category.value,
                        "min_price" to minPrice.value,
                        "day_off" to dayOff.value,
                        "online_payment" to isOnlineEnable.value,
                        "offline_payment" to isOfflineEnable.value,
                        "open_time" to startTime,
                        "close_time" to endTime,
                        "description" to description.value,
                        "email" to duplicateCheckedEmail.value,
                        "password" to password.value
                    )
                )
            ).thenThrow(InternalException(Exception()))

            onClickRegister()

            toastEvent.test().assertValue(R.string.fail_exception_internal)
        }
    }

    private fun parseTimeToDate(hour: Int, minute: Int): Date {
        val parser = SimpleDateFormat("HH:mm", Locale.KOREA)
        return parser.parse("$hour:$minute")!!
    }

    private fun formatDateToTime(time: Date): String {
        val formatter = SimpleDateFormat("HH:mm", Locale.KOREA)
        return formatter.format(time)
    }
}