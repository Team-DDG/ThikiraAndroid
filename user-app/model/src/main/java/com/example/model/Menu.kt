package com.example.model

import com.google.gson.annotations.SerializedName

data class Menu(
    @SerializedName("description")
    val description: String,
    @SerializedName("group")
    val group: List<MenuGroup>,
    @SerializedName("image")
    val image: String,
    @SerializedName("m_id")
    val menuId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int
)