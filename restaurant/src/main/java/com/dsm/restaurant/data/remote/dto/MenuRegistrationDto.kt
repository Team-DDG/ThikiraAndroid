package com.dsm.restaurant.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MenuRegistrationDto(
    @SerializedName("mc_id")
    val menuCategoryId: Int,

    val name: String,

    val price: Int,

    val description: String,

    val image: String,

    val group: List<MenuRegistrationGroupDto>
)

data class MenuRegistrationGroupDto(
    val name: String,

    @SerializedName("max_count")
    val maxCount: Int,

    val option: List<MenuRegistrationOptionDto>
)

data class MenuRegistrationOptionDto(

    val name: String,

    val price: Int
)
