package com.dsm.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "Menu",
    foreignKeys = [
        ForeignKey(
            entity = MenuCategoryEntity::class,
            parentColumns = arrayOf("menuCategoryId"),
            childColumns = arrayOf("menuCategoryId"),
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class MenuEntity(

    @PrimaryKey
    val menuId: Int,

    val menuCategoryId: Int,

    val name: String,

    val price: Int,

    val description: String,

    val image: String,

    val group: List<GroupItem>
)

data class GroupItem(

    val groupId: Int,

    val name: String,

    val maxCount: Int,

    val option: List<OptionItem>
)

data class OptionItem(

    val optionId: Int,

    val name: String,

    val price: Int
)