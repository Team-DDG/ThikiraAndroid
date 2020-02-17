package com.dsm.restaurant.presentation.ui.menu.registration

import androidx.lifecycle.*
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.data.firebase.FirebaseSource
import com.dsm.restaurant.domain.interactor.UploadMenuUseCase
import com.dsm.restaurant.domain.model.MenuCategoryModel
import com.dsm.restaurant.presentation.ui.adapter.MenuOptionRegistrationListAdapter.MenuRegistrationOption
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import com.dsm.restaurant.presentation.util.isValueBlank
import kotlinx.coroutines.launch

class MenuRegistrationViewModel(
    private val uploadMenuUseCase: UploadMenuUseCase,
    private val firebaseSource: FirebaseSource
) : ViewModel() {

    data class Group(

        val name: String,

        val max_count: Int,

        val option: ArrayList<Option> = arrayListOf()
    )

    data class Option(
        val name: String,

        val price: Int
    )

    val name = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    private val _imageUrl = MutableLiveData<String>("")
    val imageUrl: LiveData<String> = _imageUrl

    private val _menuCategoryId = MutableLiveData<Int>()
    val menuCategoryId: LiveData<Int> = _menuCategoryId

    private val _menuCategoryName = MutableLiveData<String>("")
    val menuCategoryName: LiveData<String> = _menuCategoryName

    private val _menuOptionList = MutableLiveData<ArrayList<MenuRegistrationOption>>(arrayListOf(MenuRegistrationOption.AddGroup))
    val menuOptionList: LiveData<ArrayList<MenuRegistrationOption>> = _menuOptionList

    val groupOptionList = MutableLiveData<ArrayList<Group>>(arrayListOf())

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
        firebaseSource.uploadImage(
            imagePath,
            onSuccess = { _imageUrl.value = it },
            onFailure = { _toastEvent.value = R.string.fail_image_uploading },
            onComplete = { _isImageUploading.value = false }
        )
    }

    fun onClickAddGroup() {
        _dialogAddGroupEvent.call()
    }

    fun onClickAddOption(position: Int) {
        _dialogAddOptionEvent.value = position
    }

    fun addGroup(groupName: String, maxCount: Int) {
        val list = _menuOptionList.value!!
        list.add(list.size - 1, MenuRegistrationOption.Group(groupName, maxCount))
        _menuOptionList.value = list
        groupOptionList.value?.add(Group(groupName, maxCount))
    }

    fun addOption(name: String, price: Int, position: Int) {
        val list = _menuOptionList.value!!
        val groupName = (list[position] as MenuRegistrationOption.Group).groupName
        list.add(position + 1, MenuRegistrationOption.Option(name, price))
        _menuOptionList.value = list
        groupOptionList.value?.forEachIndexed { index, groupOption ->
            if (groupOption.name == groupName) {
                groupOptionList.value?.get(index)?.option?.add(Option(name, price))
                return@forEachIndexed
            }
        }
    }

    fun deleteGroup(position: Int) {
        val list = _menuOptionList.value!!

        var lastPosition = position
        (position until list.size).forEach {
            if (list[it] is MenuRegistrationOption.Group || list[it] is MenuRegistrationOption.AddGroup) {
                return@forEach
            }
            lastPosition = it
        }

        repeat((position..lastPosition).count()) {
            list.removeAt(position)
        }

        _menuOptionList.value = list

        groupOptionList.value?.removeAt(position)
    }

    fun deleteOption(position: Int) {
        val list = _menuOptionList.value!!
        list.removeAt(position)
        _menuOptionList.value = list

        var count = 0
        for (i in 0 until list.size) {
            count++
            for (j in 0 until groupOptionList.value!![i].option.size) {
                if (position == count) {
                    groupOptionList.value!![i].option.removeAt(j)
                    return
                }
                count++
            }
        }
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