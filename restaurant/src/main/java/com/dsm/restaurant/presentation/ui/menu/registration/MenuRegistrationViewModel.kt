package com.dsm.restaurant.presentation.ui.menu.registration

import androidx.lifecycle.*
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.data.firebase.FirebaseStorageSource
import com.dsm.restaurant.domain.interactor.UploadMenuUseCase
import com.dsm.restaurant.presentation.model.MenuCategoryModel
import com.dsm.restaurant.presentation.model.MenuRegistrationModel
import com.dsm.restaurant.presentation.model.MenuRegistrationOptionModel
import com.dsm.restaurant.presentation.model.MenuRegistrationOptionModel.AddGroup
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch

class MenuRegistrationViewModel(
    private val uploadMenuUseCase: UploadMenuUseCase,
    private val firebaseStorageSource: FirebaseStorageSource
) : ViewModel() {

    private val _imageUrl = MutableLiveData<String>("")
    val imageUrl: LiveData<String> = _imageUrl

    private val _menuCategoryId = MutableLiveData<Int>()
    val menuCategoryId: LiveData<Int> = _menuCategoryId

    private val _menuCategoryName = MutableLiveData<String>("")
    val menuCategoryName: LiveData<String> = _menuCategoryName

    private val _menuOptionList = MutableLiveData<ArrayList<MenuRegistrationOptionModel>>(arrayListOf(AddGroup))
    val menuOptionList: LiveData<ArrayList<MenuRegistrationOptionModel>> = _menuOptionList

    // two-way binding
    val name = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    private val _isImageUploading = MutableLiveData<Boolean>(false)
    val isImageUploading: LiveData<Boolean> = _isImageUploading

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _dialogAddGroupEvent = SingleLiveEvent<Unit>()
    val dialogAddGroupEvent: LiveData<Unit> = _dialogAddGroupEvent

    private val _dialogAddOptionEvent = SingleLiveEvent<Pair<Int, String>>()
    val dialogAddOptionEvent: LiveData<Pair<Int, String>> = _dialogAddOptionEvent

    private val _finishActivityEvent = SingleLiveEvent<Unit>()
    val finishActivityEvent: LiveData<Unit> = _finishActivityEvent

    fun setMenuCategory(menuCategoryModel: MenuCategoryModel) {
        _menuCategoryId.value = menuCategoryModel.menuCategoryId
        _menuCategoryName.value = menuCategoryModel.name
    }

    fun uploadImage(imagePath: String) {
        _isImageUploading.value = true
        firebaseStorageSource.uploadImage(
            imagePath,
            onSuccess = { _imageUrl.value = it },
            onFailure = { _toastEvent.value = R.string.fail_image_uploading },
            onComplete = { _isImageUploading.value = false }
        )
    }

    fun onClickAddGroup() {
        _dialogAddGroupEvent.call()
    }

    fun onClickAddOption(position: Int, groupName: String) {
        _dialogAddOptionEvent.value = Pair(position, groupName)
    }

    fun onClickAddGroup(groupName: String, maxCount: Int) {
        val list = _menuOptionList.value!!
        list.add(list.size - 1, MenuRegistrationOptionModel.Group(groupName, maxCount))
        _menuOptionList.value = list
    }

    fun onClickAddOption(name: String, price: Int, position: Int, groupName: String) {
        val list = _menuOptionList.value!!
        list.add(position + 1, MenuRegistrationOptionModel.Option(name, price, groupName))
        _menuOptionList.value = list
    }

    fun onClickDeleteGroup(position: Int) {
        val list = _menuOptionList.value!!
        val groupName = (list[position] as MenuRegistrationOptionModel.Group).groupName
        list.removeAt(position)
        _menuOptionList.value = list.filter {
            !(it is MenuRegistrationOptionModel.Option && it.parentGroup == groupName)
        } as ArrayList<MenuRegistrationOptionModel>
    }

    fun onClickDeleteOption(position: Int) {
        val list = _menuOptionList.value!!
        list.removeAt(position)
        _menuOptionList.value = list
    }

    fun onClickUploadMenu() = viewModelScope.launch {
        try {
            uploadMenuUseCase(
                MenuRegistrationModel(
                    menuCategoryId = menuCategoryId.value!!,
                    image = imageUrl.value!!,
                    description = description.value!!,
                    name = name.value!!,
                    price = price.value!!.toInt(),
                    group = menuOptionList.value!!
                ).toEntity()
            )

            _finishActivityEvent.call()
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }

    }

    val isRegistrationNextClickable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(name) { value = isRegistration1Filled() }
        addSource(price) { value = isRegistration1Filled() }
        addSource(description) { value = isRegistration1Filled() }
        addSource(imageUrl ) { value = isRegistration1Filled() }
        addSource(menuCategoryName) { value = isRegistration1Filled() }
    }

    private fun isRegistration1Filled(): Boolean =
        !(name.value.isNullOrBlank() || price.value.isNullOrBlank() || description.value.isNullOrBlank()
                || imageUrl.value.isNullOrBlank() || menuCategoryName.value.isNullOrBlank())
}