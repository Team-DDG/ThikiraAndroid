package com.example.model

import com.google.gson.annotations.SerializedName

data class MenuOption(
    @SerializedName("name")
    val name: String,
    @SerializedName("o_id")
    val optionId: Int,
    @SerializedName("price")
    val price: Int
)