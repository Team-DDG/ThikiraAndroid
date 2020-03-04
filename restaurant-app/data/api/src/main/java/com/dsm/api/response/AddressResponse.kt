package com.dsm.api.response

import com.google.gson.annotations.SerializedName

data class AddressResponse(

    @SerializedName("items")
    val addresses: List<AddressItem>
)

data class AddressItem(

    val title: String,

    val address: String,

    val roadAddress: String
)