package com.dsm.setting.binding

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.dsm.model.Address
import com.dsm.setting.R

@BindingAdapter("animChangeAddress")
fun MotionLayout.animChangeAddress(newAddressLiveData: LiveData<Address>) {
    if (newAddressLiveData.value != null && this.currentState == R.id.start) {
        this.transitionToState(R.id.end)
    }
}