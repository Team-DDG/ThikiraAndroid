package com.dsm.account.viewModel

import androidx.lifecycle.*
import com.dsm.account.R
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.androidcomponent.ext.isValidEmail
import com.dsm.androidcomponent.ext.isValidPassword
import com.dsm.error.exception.ConflictException
import com.dsm.firebase.FirebaseStorageSource
import com.dsm.model.Address
import com.dsm.model.repository.AccountRepository
import com.dsm.model.repository.AuthRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class RegisterViewModel(
    private val accountRepository: AccountRepository,
    private val authRepository: AuthRepository,
    private val firebaseStorageSource: FirebaseStorageSource
) : ViewModel() {

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> = _imageUrl

    private val _address = MutableLiveData<Address>()
    val address: LiveData<Address> = _address

    private val _category = MutableLiveData<String>()
    val category: LiveData<String> = _category

    private val _isOfflineEnable = MutableLiveData<Boolean>(true)
    val isOfflineEnable: LiveData<Boolean> = _isOfflineEnable

    private val _isOnlineEnable = MutableLiveData<Boolean>(true)
    val isOnlineEnable: LiveData<Boolean> = _isOnlineEnable

    private val _duplicateCheckedEmail = MutableLiveData<String>()
    val duplicateCheckedEmail: LiveData<String> = _duplicateCheckedEmail

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
    val passwordRetype = MutableLiveData<String>("")

    val isNext1Enable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        listOf(imageUrl, restaurantName, phoneNum, address, area).forEach {
            addSource(it) {
                value = !(imageUrl.value.isNullOrBlank() || restaurantName.value.isNullOrBlank()
                        || phoneNum.value.isNullOrBlank() || address.value?.address.isNullOrBlank() || area.value.isNullOrEmpty())
            }
        }
    }

    val isNext2Enable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        listOf(category, minPrice, dayOff).forEach {
            addSource(it) {
                value = !(category.value.isNullOrBlank() || minPrice.value.isNullOrBlank()
                        || dayOff.value.isNullOrBlank())
            }
        }
    }

    val isNext3Enable: LiveData<Boolean> = Transformations.map(description) { it != "" }

    val isRegisterEnable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        listOf(password, passwordRetype).forEach {
            addSource(it) {
                value = !(password.value.isNullOrBlank() || passwordRetype.value.isNullOrBlank())
            }
        }
    }

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _popToLoginEvent = SingleLiveEvent<Unit>()
    val popToLoginEvent: LiveData<Unit> = _popToLoginEvent

    private val _isUploadingImage = MutableLiveData<Boolean>(false)
    val isUploadingImage: LiveData<Boolean> = _isUploadingImage

    fun setAddress(address: Address) {
        _address.value = address
    }

    fun setCategory(category: String) {
        _category.value = category
    }

    fun setIsOffline(value: Boolean) {
        _isOfflineEnable.value = value
    }

    fun setIsOnline(value: Boolean) {
        _isOnlineEnable.value = value
    }

    fun uploadImage(imagePath: String) = viewModelScope.launch {
        _isUploadingImage.value = true
        try {
            _imageUrl.value = firebaseStorageSource.uploadImage(imagePath)
        } catch (e: Exception) {
            _toastEvent.value = R.string.fail_image_uploading
        } finally {
            _isUploadingImage.value = false
        }
    }

    fun onClickDuplicationCheck() {
        val currentEmail = email.value

        if (currentEmail == null || currentEmail.isNullOrBlank()) {
            _toastEvent.value = R.string.fail_email_blank
            return
        }

        if (!isValidEmail(currentEmail)) {
            _toastEvent.value = R.string.fail_email_invalid
            return
        }

        checkEmailDuplication(currentEmail)
    }

    private fun checkEmailDuplication(email: String) = viewModelScope.launch {
        try {
            authRepository.confirmEmailDuplication(email)
            _toastEvent.value = R.string.success_email_duplication_check

            this@RegisterViewModel.email.value = ""
            _duplicateCheckedEmail.value = email
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ConflictException -> R.string.fail_email_conflict
                else -> R.string.fail_exception_internal
            }
        }
    }

    fun onClickRegister() {
        val startTime = formatDateToTime(parseTimeToDate(startHour.value!!, startMinute.value!!))
        val endTime = formatDateToTime(parseTimeToDate(endHour.value!!, endMinute.value!!))

        if (!isValidPassword(password.value!!)) {
            _toastEvent.value = R.string.fail_password_invalid
            return
        }

        if (password.value!! != passwordRetype.value!!) {
            _toastEvent.value = R.string.fail_re_type_different
            return
        }

        register(startTime, endTime)
    }

    private fun register(startTime: String, endTime: String) = viewModelScope.launch {
        try {
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

            _toastEvent.value = R.string.success_register_restaurant
            _popToLoginEvent.call()
        } catch (e: Exception) {
            _toastEvent.value = R.string.fail_exception_internal
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