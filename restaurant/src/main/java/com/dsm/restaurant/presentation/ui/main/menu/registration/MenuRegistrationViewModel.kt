package com.dsm.restaurant.presentation.ui.main.menu.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dsm.restaurant.R
import com.dsm.restaurant.data.firebase.FirebaseSource
import com.dsm.restaurant.domain.model.MenuCategoryModel
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import com.dsm.restaurant.presentation.util.isValueBlank

class MenuRegistrationViewModel(
    private val firebaseSource: FirebaseSource
) : ViewModel() {

    val name = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    private val _imageUrl = MutableLiveData<String>("")
    val imageUrl: LiveData<String> = _imageUrl

    private val _menuCategoryId = MutableLiveData<Int>()
    val menuCategoryId: LiveData<Int> = _menuCategoryId

    private val _menuCategoryName = MutableLiveData<String>("")
    val menuCategoryName: LiveData<String> = _menuCategoryName

    private val _isImageUploading = MutableLiveData<Boolean>(false)
    val isImageUploading: LiveData<Boolean> = _isImageUploading

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    fun setMenuCategory(menuCategoryModel: MenuCategoryModel) {
        _menuCategoryId.value = menuCategoryModel.menuCategoryId
        _menuCategoryName.value = menuCategoryModel.name
    }

    fun uploadImage(imagePath: String) {
        _isImageUploading.value = true
        firebaseSource.uploadImage(imagePath, object : FirebaseSource.UploadListener {
            override fun onSuccess(imageUrl: String) {
                _imageUrl.value = imageUrl
            }

            override fun onFailure(exception: Exception) {
                _toastEvent.value = R.string.fail_image_uploading
            }

            override fun onComplete() {
                _isImageUploading.value = false
            }

        })
    }

    val isRegistration1NextEnable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(name) { value = isRegistration1Filled() }
        addSource(price) { value = isRegistration1Filled() }
        addSource(description) { value = isRegistration1Filled() }
        addSource(imageUrl) { value = isRegistration1Filled() }
        addSource(menuCategoryName) { value = isRegistration1Filled() }
    }

    private fun isRegistration1Filled(): Boolean =
        !(name.isValueBlank() || price.isValueBlank() || description.isValueBlank()
                || imageUrl.isValueBlank() || menuCategoryName.isValueBlank())
}