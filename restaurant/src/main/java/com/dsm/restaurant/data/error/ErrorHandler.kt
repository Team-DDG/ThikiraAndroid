package com.dsm.restaurant.data.error

interface ErrorHandler {

    fun getNetworkException(e: Exception): Exception
}