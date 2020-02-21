package com.dsm.restaurant.presentation.model

data class MenuModel(

    val menuId: Int,

    val name: String,

    val price: Int,

    val description: String,

    val image: String,

    val group: List<GroupModel>
)

data class GroupModel(

    val groupId: Int,

    val name: String,

    val maxCount: Int,

    val option: List<OptionModel>
)

data class OptionModel(

    val optionId: Int,

    val name: String,

    val price: Int
)