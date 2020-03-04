package com.dsm.restaurant.trashModel

sealed class OrderedModel {

    data class Ordered(
        val name: String,
        val amount: String,
        val price: String
    ) : OrderedModel()

    data class Option(
        val name: String
    ) : OrderedModel()
}