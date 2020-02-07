package com.dsm.restaurant.domain.model

import androidx.recyclerview.widget.DiffUtil

data class AddressModel(
    val title: String,

    val address: String,

    val roadAddress: String
) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AddressModel>() {
            override fun areItemsTheSame(oldItem: AddressModel, newItem: AddressModel): Boolean =
                oldItem.roadAddress == newItem.roadAddress
                        && oldItem.address == newItem.address
                        && oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: AddressModel, newItem: AddressModel): Boolean =
                oldItem.roadAddress == newItem.roadAddress
                        && oldItem.address == newItem.address
                        && oldItem.title == newItem.title
        }
    }
}