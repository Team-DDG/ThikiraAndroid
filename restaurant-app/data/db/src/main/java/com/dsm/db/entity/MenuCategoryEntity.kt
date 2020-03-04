package com.dsm.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MenuCategory")
data class MenuCategoryEntity(

    @PrimaryKey
    val menuCategoryId: Int,

    val name: String
)