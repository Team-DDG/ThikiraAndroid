package com.dsm.api.request

import com.google.gson.annotations.SerializedName

data class MenuRegistrationRequest(

    @SerializedName("mc_id")
    val menuCategoryId: Int,

    val name: String,

    val price: Int,

    val description: String,

    val image: String,

    val group: List<GroupItem>
)

data class GroupItem(

    val name: String,

    @SerializedName("max_count")
    val maxCount: Int,

    val option: List<OptionItem>
)

data class OptionItem(

    val name: String,

    val price: Int
)