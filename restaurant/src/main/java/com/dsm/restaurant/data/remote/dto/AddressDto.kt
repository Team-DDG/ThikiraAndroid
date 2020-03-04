package com.dsm.restaurant.data.remote.dto

import com.dsm.restaurant.domain.entity.AddressEntity
import com.google.gson.annotations.SerializedName

data class AddressDto(

    @SerializedName("items")
    val results: List<AddressItemDto>
) {
    fun toEntity() = results.map(AddressItemDto::toModel)
}

data class AddressItemDto(
    val title: String,

    val address: String,

    val roadAddress: String
) {
    fun toModel() = AddressEntity(
        title = title,
        address = address,
        roadAddress = roadAddress
    )
}