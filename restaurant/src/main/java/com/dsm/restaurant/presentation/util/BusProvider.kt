package com.dsm.restaurant.presentation.util

import com.squareup.otto.Bus

object BusProvider {

    private val INSTANCE = Bus()

    fun getInstance() = INSTANCE
}