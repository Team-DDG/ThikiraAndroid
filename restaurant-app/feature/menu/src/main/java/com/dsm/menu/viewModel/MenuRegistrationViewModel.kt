package com.dsm.menu.viewModel

import androidx.lifecycle.*
import com.dsm.androidcomponent.R
import com.dsm.androidcomponent.SingleLiveEvent
import com.dsm.firebase.FirebaseStorageSource
import com.dsm.model.MenuCategory
import com.dsm.model.MenuOption
import com.dsm.model.MenuRegistration
import com.dsm.model.repository.MenuRepository
import com.example.error.exception.ForbiddenException
import kotlinx.coroutines.launch

class MenuRegistrationViewModel(
    private val menuRepository: MenuRepository,
    private val firebaseStorageSource: FirebaseStorageSource
) : ViewModel() {

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> = _imageUrl

    private val _menuCategory = MutableLiveData<MenuCategory>()
    val menuCategory: LiveData<MenuCategory> = _menuCategory

    private val _menuOptions = MutableLiveData<ArrayList<MenuOption>>(arrayListOf(MenuOption.AddGroup))
    val menuOptions: LiveData<ArrayList<MenuOption>> = _menuOptions

    // two-way binding
    val name = MutableLiveData<String>()
    val price = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    val isNextEnable: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        listOf(name, price, description, imageUrl, menuCategory).forEach {
            addSource(it) {
                value = !(name.value.isNullOrBlank() || price.value.isNullOrBlank() || description.value.isNullOrBlank()
                        || imageUrl.value.isNullOrBlank() || menuCategory.value == null)
            }
        }
    }

    private val _isImageUploading = MutableLiveData<Boolean>(false)
    val isImageUploading: LiveData<Boolean> = _isImageUploading

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    private val _popToMainEvent = SingleLiveEvent<Unit>()
    val popToMainEvent: LiveData<Unit> = _popToMainEvent

    fun setMenuCategory(menuCategory: MenuCategory) {
        _menuCategory.value = menuCategory
    }

    fun uploadImage(imagePath: String) = viewModelScope.launch {
        try {
            _isImageUploading.value = true
            _imageUrl.value = firebaseStorageSource.uploadImage(imagePath)
        } catch (e: Exception) {
            _toastEvent.value = R.string.fail_exception_internal
        } finally {
            _isImageUploading.value = false
        }
    }

    fun onClickAddGroup(groupName: String, maxCount: Int) {
        val list = _menuOptions.value!!
        list.add(list.size - 1, MenuOption.Group(groupName, maxCount))
        _menuOptions.value = list
    }

    fun onClickAddOption(name: String, price: Int, position: Int, groupName: String) {
        val list = _menuOptions.value!!
        list.add(position + 1, MenuOption.Option(name, price, groupName))
        _menuOptions.value = list
    }

    fun onClickDeleteGroup(position: Int) {
        val list = _menuOptions.value!!
        val groupName = (list[position] as MenuOption.Group).groupName
        list.removeAt(position)
        _menuOptions.value = list.filter {
            !(it is MenuOption.Option && it.parentGroup == groupName)
        } as ArrayList<MenuOption>
    }

    fun onClickDeleteOption(position: Int) {
        val list = _menuOptions.value!!
        list.removeAt(position)
        _menuOptions.value = list
    }

    fun onClickUploadMenu() = viewModelScope.launch {
        try {
            menuRepository.uploadMenu(
                MenuRegistration(
                    menuCategoryId = menuCategory.value?.menuCategoryId ?: 0,
                    image = _imageUrl.value ?: "",
                    description = description.value ?: "",
                    name = name.value ?: "",
                    price = price.value!!.toInt(),
                    group = _menuOptions.value!!
                )
            )

            _popToMainEvent.call()
        } catch (e: Exception) {
            _toastEvent.value = when (e) {
                is ForbiddenException -> R.string.fail_exception_forbidden
                else -> R.string.fail_exception_internal
            }
        }
    }
}