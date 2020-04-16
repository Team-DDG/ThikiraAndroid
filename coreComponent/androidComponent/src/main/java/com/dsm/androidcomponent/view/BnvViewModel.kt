package com.dsm.androidcomponent.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BnvViewModel : ViewModel() {
    private val _isBnvVisible = MutableLiveData(true)
    val isBnvVisible: LiveData<Boolean> = _isBnvVisible

    fun hideBnv() {
        _isBnvVisible.value = false
    }

    fun showBnv() {
        _isBnvVisible.value = true
    }
}