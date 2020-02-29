package com.dsm.mapper

import com.dsm.api.response.AddressResponse
import com.dsm.model.Address

internal fun AddressResponse.toAddresses(): List<Address> =
    this.addresses.map {
        Address(
            title = it.title.replace("<b>", "").replace("</b>", ""),
            address = it.address,
            roadAddress = it.roadAddress
        )
    }