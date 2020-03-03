package com.dsm.model

sealed class MenuOption {

    data class Group(val groupName: String, val maxCount: Int) : MenuOption()

    data class Option(val optionName: String, val price: Int, val parentGroup: String) : MenuOption()

    object AddGroup : MenuOption()
}

data class MenuRegistration(

    val menuCategoryId: Int,

    val name: String,

    val price: Int,

    val description: String,

    val image: String,

    val group: List<MenuOption>
)