package com.example

import com.google.gson.annotations.SerializedName

data class MenuList(
    @SerializedName("mc_id")
    val menuCategoryId: Int,
    @SerializedName("name")
    val name: String
)