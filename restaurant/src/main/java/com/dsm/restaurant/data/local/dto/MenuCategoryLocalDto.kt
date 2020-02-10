package com.dsm.restaurant.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuCategoryLocalDto(

    @PrimaryKey
    val menuCategoryId: Int,

    val name: String
)