package com.dsm.restaurant.data.remote.dto

import com.dsm.restaurant.domain.model.AddressModel
import com.google.gson.annotations.SerializedName

data class AddressDto(

    @SerializedName("items")
    val results: List<AddressItemDto>
) {
    fun toModel() = results.map(AddressItemDto::toModel)
}

data class AddressItemDto(
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