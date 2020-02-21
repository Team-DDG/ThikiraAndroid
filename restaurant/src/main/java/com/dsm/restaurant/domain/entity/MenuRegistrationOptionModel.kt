package com.dsm.restaurant.domain.entity

sealed class MenuRegistrationOptionModel {

    data class Group(val groupName: String, val maxCount: Int) : MenuRegistrationOptionModel()

    data class Option(val optionName: String, val price: Int) : MenuRegistrationOptionModel()

    object AddGroup : MenuRegistrationOptionModel()
}