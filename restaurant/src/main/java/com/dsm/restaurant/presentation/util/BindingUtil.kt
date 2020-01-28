package com.dsm.restaurant.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import mabbas007.tagsedittext.TagsEditText

@BindingAdapter("image")
fun ImageView.bindImage(url: LiveData<String>?) {
    url?.let {
        Glide.with(this).load(it.value).into(this)
    }
}

@BindingAdapter("tags")
fun TagsEditText.bindTags(tagsMutableLiveData: MutableLiveData<List<String>>?) {
    tagsMutableLiveData?.value = this.tags.toList()
}
