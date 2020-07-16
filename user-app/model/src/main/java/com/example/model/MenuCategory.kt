package com.example.model

import com.google.gson.annotations.SerializedName

data class MenuCategory(
    @SerializedName("mc_id")
    val menuCategoryId: Int,
    @SerializedName("name")
    val name: String
)