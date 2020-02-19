package com.dsm.restaurant.presentation.ui.register

import androidx.lifecycle.*
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ConflictException
import com.dsm.restaurant.data.firebase.FirebaseSource
import com.dsm.restaurant.domain.interactor.ConfirmEmailDuplicationUseCase
import com.dsm.restaurant.domain.interactor.RegisterUseCase
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import com.dsm.restaurant.presentation.util.isValidEmail
import com.dsm.restaurant.presentation.util.isValidPassword
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class RegisterViewModel(
    private val confirmEmailDuplicationUseCase: ConfirmEmailDuplicationUseCase,
    private val registerUseCase: RegisterUseCase,
    private val firebaseSource: FirebaseSource
) : ViewModel() {

    private val _imageUrl = MutableLiveData<String>("")
    val imageUrl: LiveData<String> = _imageUrl

    private val _address = MutableLiveData<String>("")
    val address: LiveData<String> = _address

    private val _roadAddress = MutableLiveData<String>()
    val roadAddress: LiveData<String> = _roadAddress

    private val _category = MutableLiveData<String>("")
    val category: LiveData<String> = _category

    private val _isOfflineEnable = MutableLiveData<Boolean>(true)
    val isOfflineEnable: LiveData<Boolean> = _isOfflineEnable

    private val _isOnlineEnable = MutableLiveData<Boolean>(true)
    val isOnlineEnable: LiveData<Boolean> = _isOnlineEnable

    // two-way binding
    val restaurantName = MutableLiveData<String>()
    val phoneNum = MutableLiveData<String>()
    val area = MutableLiveData<List<String>>()
    val minPrice = MutableLiveData<String>()
    val dayOff = MutableLiveData<String>()
    val startHour = MutableLiveData(0)
    val startMinute = MutableLiveData(0)
    val endHour = MutableLiveData(0)
    val endMinute = MutableLiveData(0)
    val description = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>("")
    val reTypePwd = MutableLiveData<String>("")

    private val _snackbarEvent = SingleLiveEvent<Int>()
    val snackbarEvent: LiveData<Int> = _snackbarEvent

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _animatePassword = SingleLiveEvent<Unit>()
    val animatePassword: LiveData<Unit> = _animatePassword

    /**
     * Binding
     */
    fun setAddress(address: String, roadAddress: String) {
        _address.value = address
        _roadAddress.value = roadAddress
    }

    fun onSelectCategory(category: String) {
        _category.value = category
    }

    fun setIsOfflineEnable(value: Boolean) {
        _isOfflineEnable.value = value
    }

    fun setIsOnlineEnable(value: Boolean) {
        _isOnlineEnable.value = value
    }

    /**
     * 영업시간 유효성 확인
     */
    val isBusinessHourValid: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(startHour) { value = storeHoursValidation() }
        addSource(startMinute) { value = storeHoursValidation() }
        addSource(endHour) { value = storeHoursValidation() }
        addSource(endMinute) { value = storeHoursValidation() }
        value = false
    }

    private fun parseTimeToDate(hour: Int, minute: Int): Date {
        val parser = SimpleDateFormat("HH:mm", Locale.KOREA)
        return parser.parse("$hour:$minute")!!
    }

    private fun storeHoursValidation(): Boolean {
        val startTime = parseTimeToDate(startHour.value ?: 0, startMinute.value ?: 0)
        val endTime = parseTimeToDate(endHour.value ?: 0, endMinute.value ?: 0)
        return startTime.time < endTime.time
    }

    /**
     * 파이어베이스 이미지 업로딩
     */
    private val _isUploadingImage = MutableLiveData<Boolean>().apply { value = false }
    val isUploadingImage: LiveData<Boolean> = _isUploadingImage

    fun uploadImage(imagePath: String) {
        _isUploadingImage.value = true
        firebaseSource.uploadImage(
            imagePath,
            onSuccess = { _imageUrl.value = it },
            onFailure = { _toastEvent.value = R.string.fail_image_uploading },
            onComplete = { _isUploadingImage.value = false }
        )
    }

    /**
     * 이메일 검증 및 중복 검사
     */
    private val _checkedEmail = MutableLiveData<String>("")
    val checkedEmail: LiveData<String> = _checkedEmail

    fun onClickDuplicationCheck() {
        val currentEmail = email.value

        if (currentEmail == null || currentEmail.isNullOrBlank()) {
            _snackbarEvent.value = R.string.fail_email_blank
            return
        }

        if (!isValidEmail(currentEmail)) {
            _snackbarEvent.value = R.string.fail_email_invalid
            return
        }

        checkEmailDuplication(currentEmail)
    }

    private fun checkEmailDuplication(email: String) = viewModelScope.launch {
        try {
            confirmEmailDuplicationUseCase(email)
            _toastEvent.value = R.string.success_email_duplication_check

            this@RegisterViewModel.email.value = ""
            _checkedEmail.value = email
            _animatePassword.call()
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ConflictException -> R.string.fail_email_conflict
                else -> R.string.fail_exception_internal
            }
        }
    }

    /**
     * 업체 등록
     */
    private val _navigateLogin = SingleLiveEvent<Unit>()
    val navigateLogin: LiveData<Unit> = _navigateLogin

    fun onClickRegister() {
        val startTime = timeToString(parseTimeToDate(startHour.value!!, startMinute.value!!))
        val endTime = timeToString(parseTimeToDate(endHour.value!!, endMinute.value!!))

        if (!isValidPassword(password.value!!)) {
            _toastEvent.value = R.string.fail_password_invalid
            return
        }

        if (!isPasswordReTypeSame()) {
            _toastEvent.value = R.string.fail_re_type_different
            return
        }

        register(startTime, endTime)
    }

    private fun register(startTime: String, endTime: String) = viewModelScope.launch {
        try {
            registerUseCase(
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
                    "open_time" to startTime,
                    "close_time" to endTime,
                    "description" to description.value,
                    "email" to checkedEmail.value,
                    "password" to password.value
                )
            )

            _toastEvent.value = R.string.success_register_restaurant
            _navigateLogin.call()
        } catch (e: Exception) {
            _toastEvent.value = R.string.fail_exception_internal
        }
    }

    private fun isPasswordReTypeSame() = password.value!! == reTypePwd.value!!

    val isPasswordReTypeCorrect: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(password) { value = isPasswordReTypeSame() }
        addSource(reTypePwd) { value = isPasswordReTypeSame() }
    }

    private fun timeToString(time: Date): String {
        val formatter = SimpleDateFormat("HH:mm", Locale.KOREA)
        return formatter.format(time)
    }

    /**
     * 버튼 활성화 체크
     */

    val isNext1Clickable = MediatorLiveData<Boolean>().apply {
        addSource(imageUrl) { value = isRegister1Filled() }
        addSource(restaurantName) { value = isRegister1Filled() }
        addSource(phoneNum) { value = isRegister1Filled() }
        addSource(address) { value = isRegister1Filled() }
        addSource(area) { value = isRegister1Filled() }
    }

    private fun isRegister1Filled(): Boolean =
        !(imageUrl.value.isNullOrBlank() || restaurantName.value.isNullOrBlank()
                || phoneNum.value.isNullOrBlank() || address.value.isNullOrBlank() || area.value.isNullOrEmpty())

    val isNext2Clickable = MediatorLiveData<Boolean>().apply {
        addSource(category) { value = isRegister2Filled() }
        addSource(minPrice) { value = isRegister2Filled() }
        addSource(dayOff) { value = isRegister2Filled() }
        addSource(isBusinessHourValid) { value = isRegister2Filled() }
    }

    private fun isRegister2Filled(): Boolean =
        !(category.value.isNullOrBlank() || minPrice.value.isNullOrBlank()
                || dayOff.value.isNullOrBlank() || isBusinessHourValid.value == false)

    val isNext3Clickable: LiveData<Boolean> = Transformations.map(description) { it != "" }

    val isRegisterClickable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(email) { value = isRegister4Filled() }
        addSource(password) { value = isRegister4Filled() }
        addSource(reTypePwd) { value = isRegister4Filled() }
    }

    private fun isRegister4Filled(): Boolean =
        !(checkedEmail.value.isNullOrBlank() || password.value.isNullOrBlank() || reTypePwd.value.isNullOrBlank())
}