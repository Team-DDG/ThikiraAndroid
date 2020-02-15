package com.dsm.restaurant.presentation.model

data class GroupOptionModel(

    val name: String,

    val max_count: Int,

    val option: ArrayList<OptionModel> = arrayListOf()
)

data class OptionModel(
    val name: String,

    val price: Int
)