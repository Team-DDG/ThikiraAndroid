package com.dsm.restaurant

import androidx.recyclerview.widget.DiffUtil

data class CouponModel(

    val couponId: Int,

    val status: String,

    val expireDate: String,

    val discountPrice: String
) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CouponModel>() {
            override fun areItemsTheSame(oldItem: CouponModel, newItem: CouponModel): Boolean =
                oldItem.couponId == newItem.couponId

            override fun areContentsTheSame(oldItem: CouponModel, newItem: CouponModel): Boolean =
                oldItem == newItem

        }
    }

    override fun equals(other: Any?): Boolean =
        if (other is CouponModel)
            (status == other.status &&
                    expireDate == other.expireDate &&
                    discountPrice == other.discountPrice)
        else false


    override fun hashCode(): Int {
        var result = couponId
        result = 31 * result + status.hashCode()
        result = 31 * result + expireDate.hashCode()
        result = 31 * result + discountPrice.hashCode()
        return result
    }
}