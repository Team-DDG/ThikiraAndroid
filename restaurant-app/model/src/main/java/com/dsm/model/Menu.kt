package com.dsm.model

data class Menu(

    val menuId: Int,

    val name: String,

    val price: Int,

    val description: String,

    val image: String,

    val group: List<MenuGroupItem>
)

data class MenuGroupItem(

    val groupId: Int,

    val name: String,

    val maxCount: Int,

    val option: List<MenuOptionItem>
)

data class MenuOptionItem(

    val optionId: Int,

    val name: String,

    val price: Int
)