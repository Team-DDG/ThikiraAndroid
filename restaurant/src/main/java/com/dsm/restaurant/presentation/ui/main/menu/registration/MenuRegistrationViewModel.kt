package com.dsm.restaurant.presentation.ui.main.menu.registration

import androidx.lifecycle.*
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.data.firebase.FirebaseSource
import com.dsm.restaurant.domain.interactor.UploadMenuUseCase
import com.dsm.restaurant.domain.model.MenuCategoryModel
import com.dsm.restaurant.presentation.model.GroupOptionModel
import com.dsm.restaurant.presentation.model.MenuRegistrationOptionModel
import com.dsm.restaurant.presentation.model.OptionModel
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import com.dsm.restaurant.presentation.util.isValueBlank
import kotlinx.coroutines.launch

class MenuRegistrationViewModel(
    private val uploadMenuUseCase: UploadMenuUseCase,
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

    private val _menuOptionList = MutableLiveData<ArrayList<MenuRegistrationOptionModel>>(arrayListOf(MenuRegistrationOptionModel.AddGroup))
    val menuOptionList: LiveData<ArrayList<MenuRegistrationOptionModel>> = _menuOptionList

    private val groupOptionList = MutableLiveData<ArrayList<GroupOptionModel>>(arrayListOf())

    private val _isImageUploading = MutableLiveData<Boolean>(false)
    val isImageUploading: LiveData<Boolean> = _isImageUploading

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _dialogAddGroupEvent = SingleLiveEvent<Unit>()
    val dialogAddGroupEvent: LiveData<Unit> = _dialogAddGroupEvent

    private val _dialogAddOptionEvent = SingleLiveEvent<Int>()
    val dialogAddOptionEvent: LiveData<Int> = _dialogAddOptionEvent

    private val _finishActivityEvent = SingleLiveEvent<Unit>()
    val finishActivityEvent: LiveData<Unit> = _finishActivityEvent

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

    fun onClickAddGroup() {
        _dialogAddGroupEvent.call()
    }

    fun onClickAddOption(position: Int) {
        _dialogAddOptionEvent.value = position
    }

    fun addGroup(groupName: String, maxCount: Int) {
        val list = _menuOptionList.value!!
        list.add(list.size - 1, MenuRegistrationOptionModel.Group(groupName, maxCount))
        groupOptionList.value?.add(
            GroupOptionModel(
                name = groupName,
                max_count = maxCount
            )
        )
        _menuOptionList.value = list
    }

    fun addOption(name: String, price: Int, position: Int) {
        val list = _menuOptionList.value!!
        val groupName = (list[position] as MenuRegistrationOptionModel.Group).groupName
        list.add(position + 1, MenuRegistrationOptionModel.Option(name, price))
        groupOptionList.value?.forEachIndexed { index, groupOption ->
            if (groupOption.name == groupName) {
                groupOptionList.value?.get(index)?.option?.add(
                    OptionModel(
                        name = name,
                        price = price
                    )
                )
                return@forEachIndexed
            }
        }
        _menuOptionList.value = list
    }

    fun deleteGroup(position: Int) {
        val list = _menuOptionList.value!!

        var lastPosition = position
        (position until list.size).forEach {
            if (list[it] is MenuRegistrationOptionModel.Group || list[it] is MenuRegistrationOptionModel.AddGroup) {
                return@forEach
            }
            lastPosition = it
        }

        (position..lastPosition).forEach {
            list.removeAt(position)
        }

        _menuOptionList.value = list
    }

    fun deleteOption(position: Int) {
        val list = _menuOptionList.value!!
        list.removeAt(position)
        _menuOptionList.value = list
    }

    fun uploadMenu() = viewModelScope.launch {
        try {
            uploadMenuUseCase(
                hashMapOf(
                    "mc_id" to menuCategoryId.value,
                    "name" to name.value,
                    "price" to price.value?.toInt(),
                    "description" to description.value,
                    "image" to imageUrl.value,
                    "group" to groupOptionList.value
                )
            )

            _finishActivityEvent.call()
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }

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