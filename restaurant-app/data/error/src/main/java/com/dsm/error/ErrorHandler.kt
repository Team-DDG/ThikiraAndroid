package com.dsm.error

interface ErrorHandler {
    fun getNetworkException(throwable: Throwable): Exception
}