package com.dsm.model

data class Menu(

    val menuId: Int,

    val name: String,

    val price: Int,

    val description: String,

    val image: String,

    val group: List<Group>
)

data class Group(

    val groupId: Int,

    val name: String,

    val maxCount: Int,

    val option: List<Option>
)

data class Option(

    val optionId: Int,

    val name: String,

    val price: Int
)