package com.dsm.restaurant.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import mabbas007.tagsedittext.TagsEditText

object BindingUtil {

    @JvmStatic
    @BindingAdapter("image")
    fun ImageView.bindImage(url: LiveData<String>?) {
        url?.let {
            Glide.with(this).load(it.value).into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("tags")
    fun TagsEditText.bindTags(tagsLiveData: MutableLiveData<List<String>>?) {
        tagsLiveData?.value = this.tags.toList()
//        this.setTagsListener(object: TagsEditText.TagsEditListener {
//            override fun onTagsChanged(tags: MutableCollection<String>?) {
//                tagsLiveData?.value = tags?.toList()
//            }
//
//            override fun onEditingFinished() {
//            }
//
//        })
    }
}