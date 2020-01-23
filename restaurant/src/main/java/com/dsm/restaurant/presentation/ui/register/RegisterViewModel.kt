package com.dsm.restaurant.presentation.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsm.restaurant.R
import com.dsm.restaurant.data.error.exception.ConflictException
import com.dsm.restaurant.domain.interactor.CheckEmailUseCase
import com.dsm.restaurant.presentation.util.SingleLiveEvent
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.util.regex.Pattern

class RegisterViewModel(
    private val checkEmailUseCase: CheckEmailUseCase
) : ViewModel() {

    val email = MutableLiveData<String>()

    private val _imageUrl = MutableLiveData<String>().apply { value = "" }
    val imageUrl: LiveData<String> = _imageUrl

    private val _isUploadingImage = MutableLiveData<Boolean>().apply { value = false }
    val isUploadingImage: LiveData<Boolean> = _isUploadingImage

    private val _snackbarEvent = SingleLiveEvent<Int>()
    val snackbarEvent: LiveData<Int> = _snackbarEvent

    private val _toastEvent = SingleLiveEvent<Int>()
    val toastEvent: LiveData<Int> = _toastEvent

    fun uploadImage(imagePath: String) {
        val ref = FirebaseStorage.getInstance().reference.child("/restaurant")
            .child(System.currentTimeMillis().toString() + ".png")

        _isUploadingImage.value = true
        ref.putStream(FileInputStream(File(imagePath)))
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener { _imageUrl.value = it.toString() }
            }
            .addOnFailureListener { _toastEvent.value = R.string.fail_image_uploading }
            .addOnCompleteListener { _isUploadingImage.value = false }
    }

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
        Pattern.compile("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
            .matcher(email)
            .find()

    private fun checkEmailDuplication(email: String) {
        viewModelScope.launch {
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
}