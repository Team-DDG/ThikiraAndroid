package com.dsm.account.util

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.dsm.account.R
import mabbas007.tagsedittext.TagsEditText

@BindingAdapter("tags")
fun TagsEditText.bindTags(tagsMutableLiveData: MutableLiveData<List<String>>?) {
    tagsMutableLiveData?.value = this.tags.toList()
}

@BindingAdapter("passwordAnim")
fun MotionLayout.animPassword(isEmailChecked: Boolean) {
    if (isEmailChecked && this.currentState == R.id.start) {
        this.transitionToState(R.id.end)
    }
}
