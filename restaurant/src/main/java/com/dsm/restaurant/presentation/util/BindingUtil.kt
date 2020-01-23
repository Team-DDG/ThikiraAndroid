package com.dsm.restaurant.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide

object BindingUtil {

    @JvmStatic
    @BindingAdapter("image")
    fun ImageView.bindImage(url: LiveData<String>?) {
        url?.let {
            Glide.with(this).load(it.value).into(this)
        }
    }
}