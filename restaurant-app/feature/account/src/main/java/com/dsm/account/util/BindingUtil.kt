package com.dsm.account.util

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import mabbas007.tagsedittext.TagsEditText

@BindingAdapter("tags")
fun TagsEditText.bindTags(tagsMutableLiveData: MutableLiveData<List<String>>?) {
    tagsMutableLiveData?.value = this.tags.toList()
}