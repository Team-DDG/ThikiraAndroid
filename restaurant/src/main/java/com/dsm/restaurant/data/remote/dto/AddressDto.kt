package com.dsm.restaurant.data.remote.dto

import com.dsm.restaurant.domain.model.AddressModel
import com.google.gson.annotations.SerializedName

data class AddressDto(

    @SerializedName("items")
    val results: List<AddressItems>
) {
    fun mapToModel() =
        results.map {
            AddressModel(
                title = it.title,
                address = it.address,
                roadAddress = it.roadAddress
            )
        }
}

data class AddressItems(
    val title: String,

    val address: String,

    val roadAddress: String
)