package com.dsm.restaurant.presentation.util

import androidx.lifecycle.LiveData

fun LiveData<String>.isValueBlank() =
    this.value.isNullOrBlank()
