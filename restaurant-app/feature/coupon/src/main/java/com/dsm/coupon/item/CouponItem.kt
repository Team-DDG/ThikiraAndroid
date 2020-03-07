package com.dsm.coupon.item

import com.dsm.coupon.R
import com.dsm.coupon.databinding.ItemCouponBinding
import com.dsm.model.Coupon
import com.xwray.groupie.databinding.BindableItem
import java.text.SimpleDateFormat
import java.util.*

class CouponItem(
    private val coupon: Coupon
) : BindableItem<ItemCouponBinding>() {

    override fun getLayout(): Int = R.layout.item_coupon

    override fun bind(viewBinding: ItemCouponBinding, position: Int) {
        val format = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)

        viewBinding.coupon = coupon
        viewBinding.expireDateText = format.format(coupon.expireDate)
    }


}