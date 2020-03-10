package com.dsm.mapper

import com.dsm.api.response.RestaurantResponse
import com.dsm.db.entity.AddressEntity
import com.dsm.db.entity.RestaurantEntity
import com.dsm.model.Restaurant

internal fun RestaurantEntity.toRestaurant() =
    Restaurant(
        name = name,
        address = address.address,
        roadAddress = address.roadAddress,
        description = description,
        image = image,
        deliverableArea = deliverableArea,
        isOnlinePayment = isOnlinePayment,
        isOfflinePayment = isOfflinePayment,
        category = category,
        closeTime = closeTime,
        dayOff = dayOff,
        email = email,
        minPrice = minPrice,
        openTime = openTime,
        phone = phone
    )


internal fun RestaurantResponse.toEntity() =
    RestaurantEntity(
        name = name,
        address = AddressEntity(roadAddress = roadAddress, address = address),
        description = description,
        image = image,
        deliverableArea = deliverableArea,
        isOnlinePayment = isOnlinePayment,
        isOfflinePayment = isOfflinePayment,
        category = category,
        closeTime = closeTime,
        dayOff = dayOff,
        email = email,
        minPrice = minPrice,
        openTime = openTime,
        phone = phone
    )