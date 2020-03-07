package com.dsm.coupon.binding

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.dsm.coupon.item.CouponItem
import com.dsm.model.Coupon
import com.xwray.groupie.GroupAdapter

@BindingAdapter("coupons")
fun RecyclerView.bindCoupons(couponsLiveData: LiveData<List<Coupon>>) {
    couponsLiveData.value?.let {
        (adapter as GroupAdapter).update(it.sortedByDescending { it.expireDate }.map { CouponItem(it) })
    }
}