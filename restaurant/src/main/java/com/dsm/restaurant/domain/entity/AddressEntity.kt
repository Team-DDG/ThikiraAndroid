package com.dsm.restaurant.domain.entity

import com.dsm.restaurant.presentation.model.AddressModel

data class AddressEntity(
    val title: String,

    val address: String,

    val roadAddress: String
) {
    fun toModel() = AddressModel(
        title = title,
        address = address,
        roadAddress = roadAddress
    )
}