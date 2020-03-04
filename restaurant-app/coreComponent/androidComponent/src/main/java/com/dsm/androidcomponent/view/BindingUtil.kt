package com.dsm.androidcomponent.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun ImageView.setImage(url: LiveData<String>?) {
    url?.let {
        Glide.with(this).load(it.value).into(this)
    }
}

@BindingAdapter("image")
fun ImageView.setImage(url: String?) {
    url?.let {
        Glide.with(this).load(it).into(this)
    }
}