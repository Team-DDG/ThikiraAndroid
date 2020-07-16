package com.example.model

import com.google.gson.annotations.SerializedName

data class MenuGroup(
    @SerializedName("g_id")
    val groupId: Int,
    @SerializedName("max_count")
    val maxCount: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("option")
    val option: List<MenuOption>
)