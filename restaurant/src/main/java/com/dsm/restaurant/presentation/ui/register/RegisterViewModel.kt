package com.dsm.restaurant.presentation.ui.register

import androidx.lifecycle.*
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ConflictException
import com.dsm.restaurant.data.firebase.FirebaseSource
import com.dsm.restaurant.domain.interactor.CheckEmailUseCase
import com.dsm.restaurant.domain.interactor.RegisterUseCase
import com.dsm.restaurant.domain.interactor.SearchAddressUseCase
import com.dsm.restaurant.domain.model.AddressModel
import com.dsm.restaurant.presentation.util.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class RegisterViewModel(
    private val checkEmailUseCase: CheckEmailUseCase,
    private val searchAddressUseCase: SearchAddressUseCase,
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

    // 양방향 바인딩을 위해 노출
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

    /**
     * Binding
     */
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
    val isStoreHoursValid: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
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
        EspressoIdlingResource.increment()
        firebaseSource.uploadImage(imagePath, object : FirebaseSource.UploadListener {
            override fun onSuccess(imageUrl: String) {
                _imageUrl.value = imageUrl
            }

            override fun onFailure(exception: java.lang.Exception) {
                _toastEvent.value = R.string.fail_image_uploading
            }

            override fun onComplete() {
                _isUploadingImage.value = false
                EspressoIdlingResource.decrement()
            }

        })
    }

    /**
     * 주소 검색 및 선택
     */
    val addressSearch = MutableLiveData<String>()

    val isAddressSearchable: LiveData<Boolean> = Transformations.map(addressSearch) { it != "" }

    private val _addressList = MutableLiveData<List<AddressModel>>()
    val addressList: LiveData<List<AddressModel>> = _addressList

    private val _isSearchingAddress = MutableLiveData<Boolean>()
    val isSearchingAddress: LiveData<Boolean> = _isSearchingAddress

    fun onClickSearchAddress() = viewModelScope.launch {
        try {
            _isSearchingAddress.value = true
            _addressList.value = searchAddressUseCase(addressSearch.value ?: "")
            _isSearchingAddress.value = false
        } catch (e: Exception) {
            _isSearchingAddress.value = false
            _toastEvent.value = R.string.fail_internal
        }
    }

    fun onSelectRestaurantAddress(address: String, roadAddress: String) {
        _address.value = address
        _roadAddress.value = roadAddress
    }

    /**
     * 이메일 검증 및 중복 검사
     */
    private val _checkedEmail = MutableLiveData<String>("")
    val checkedEmail: LiveData<String> = _checkedEmail

    fun checkEmail() {
        val currentEmail = email.value

        if (currentEmail == null || currentEmail.isNullOrBlank()) {
            _snackbarEvent.value = R.string.fail_blank_email
            return
        }

        if (!isValidEmail(currentEmail)) {
            _snackbarEvent.value = R.string.fail_invalid_email
            return
        }

        checkEmailDuplication(currentEmail)
    }

    private fun checkEmailDuplication(email: String) = viewModelScope.launch {
        try {
            checkEmailUseCase(email)
            _toastEvent.value = R.string.success_email_check

            this@RegisterViewModel.email.value = ""
            _checkedEmail.value = email
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ConflictException -> R.string.fail_conflict_email
                else -> R.string.fail_internal
            }
        }
    }

    /**
     * 업체 등록
     */
    private val _popToLoginEvent = SingleLiveEvent<Unit>()
    val popToLoginEvent: LiveData<Unit> = _popToLoginEvent

    fun register() = viewModelScope.launch {
        val startTime = parseTimeToDate(startHour.value!!, startMinute.value!!)
        val endTime = parseTimeToDate(endHour.value!!, endMinute.value!!)

        if (!isValidPassword(password.value!!)) {
            _toastEvent.value = R.string.fail_password_invalid
            return@launch
        }

        if (!checkPasswordReType()) {
            _toastEvent.value = R.string.fail_diff_retype
            return@launch
        }

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
                    "open_time" to timeToString(startTime),
                    "close_time" to timeToString(endTime),
                    "description" to description.value,
                    "email" to checkedEmail.value,
                    "password" to password.value
                )
            )

            _toastEvent.value = R.string.success_register_restaurant
            _popToLoginEvent.call()
        } catch (e: Exception) {
            _toastEvent.value = R.string.fail_internal
        }
    }

    private fun checkPasswordReType() = password.value!! == reTypePwd.value!!

    val isPasswordReTypeCorrect: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(password) { value = checkPasswordReType() }
        addSource(reTypePwd) { value = checkPasswordReType() }
    }

    private fun timeToString(time: Date): String {
        val formatter = SimpleDateFormat("HH:mm", Locale.KOREA)
        return formatter.format(time)
    }

    /**
     * 버튼 활성화 체크
     */
    private fun isRegister1Filled(): Boolean =
        !(imageUrl.isValueBlank() || restaurantName.isValueBlank()
                || phoneNum.isValueBlank() || address.isValueBlank()
                || area.value.isNullOrEmpty())

    val isNext1Enabled = MediatorLiveData<Boolean>().apply {
        addSource(imageUrl) { value = isRegister1Filled() }
        addSource(restaurantName) { value = isRegister1Filled() }
        addSource(phoneNum) { value = isRegister1Filled() }
        addSource(address) { value = isRegister1Filled() }
        addSource(area) { value = isRegister1Filled() }
    }

    private fun isRegister2Filled(): Boolean =
        !(category.isValueBlank() || minPrice.isValueBlank()
                || dayOff.isValueBlank() || isStoreHoursValid.value == false)

    val isNext2Enabled = MediatorLiveData<Boolean>().apply {
        addSource(category) { value = isRegister2Filled() }
        addSource(minPrice) { value = isRegister2Filled() }
        addSource(dayOff) { value = isRegister2Filled() }
        addSource(isStoreHoursValid) { value = isRegister2Filled() }
    }

    val isNext3Enabled: LiveData<Boolean> = Transformations.map(description) { it != "" }

    private fun isRegister4Filled(): Boolean =
        !(checkedEmail.isValueBlank() || password.isValueBlank() || reTypePwd.isValueBlank())

    val isRegisterEnabled: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(email) { value = isRegister4Filled() }
        addSource(password) { value = isRegister4Filled() }
        addSource(reTypePwd) { value = isRegister4Filled() }
    }
}