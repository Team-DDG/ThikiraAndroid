package com.dsm.restaurant.presentation.ui.register

import androidx.lifecycle.*
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ConflictException
import com.dsm.restaurant.data.firebase.FirebaseSource
import com.dsm.restaurant.domain.interactor.CheckEmailUseCase
import com.dsm.restaurant.domain.interactor.SearchAddressUseCase
import com.dsm.restaurant.domain.model.AddressModel
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import com.dsm.restaurant.presentation.util.isValueBlank
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegisterViewModel(
    private val checkEmailUseCase: CheckEmailUseCase,
    private val searchAddressUseCase: SearchAddressUseCase,
    private val firebaseSource: FirebaseSource
) : ViewModel() {

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> = _imageUrl

    private val _address = MutableLiveData<String>()
    val address: LiveData<String> = _address

    private val _roadAddress = MutableLiveData<String>()
    val roadAddress: LiveData<String> = _roadAddress

    // 양방향 바인딩을 위해 노출
    val restaurantName = MutableLiveData<String>()
    val phoneNum = MutableLiveData<String>()
    val area = MutableLiveData<List<String>>()
    val email = MutableLiveData<String>()
    val category = MutableLiveData<String>()

    private val _snackbarEvent = SingleLiveEvent<Int>()
    val snackbarEvent: LiveData<Int> = _snackbarEvent

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

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

    /**
     * 카테고리
     */
    fun onSelectCategory(category: String) {
        this.category.value = category
    }

    /**
     * 파이어베이스 이미지 업로딩
     */
    private val _isUploadingImage = MutableLiveData<Boolean>().apply { value = false }
    val isUploadingImage: LiveData<Boolean> = _isUploadingImage

    fun uploadImage(imagePath: String) {
        _isUploadingImage.value = true
        firebaseSource.uploadImage(imagePath, object: FirebaseSource.UploadListener {
            override fun onSuccess(imageUrl: String) {
                _imageUrl.value = imageUrl
            }

            override fun onFailure(exception: java.lang.Exception) {
                _toastEvent.value = R.string.fail_image_uploading
            }

            override fun onComplete() {
                _isUploadingImage.value = false
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
    fun checkEmail() {
        val currentEmail = email.value

        if (currentEmail == null || currentEmail.isNullOrBlank()) {
            _snackbarEvent.value = R.string.fail_blank_email
            return
        }

        if (!isEmailValid(currentEmail)) {
            _snackbarEvent.value = R.string.fail_invalid_email
            return
        }

        checkEmailDuplication(currentEmail)
    }

    private fun isEmailValid(email: String): Boolean =
        Pattern.compile("^[0-9a-zA-Z]*[-_.]?[0-9a-zA-Z]*@[0-9a-zA-Z]*[-_.]?[0-9a-zA-Z]*.[a-zA-Z]{2,3}$")
            .matcher(email)
            .find()

    private fun checkEmailDuplication(email: String) = viewModelScope.launch {
        try {
            checkEmailUseCase(email)
            _toastEvent.value = R.string.success_email_check
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ConflictException -> R.string.fail_conflict_email
                else -> R.string.fail_internal
            }
        }
    }
}