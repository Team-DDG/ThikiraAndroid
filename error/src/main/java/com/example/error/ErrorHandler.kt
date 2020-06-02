package com.example.error

interface ErrorHandler {
    fun getNetworkException(throwable: Throwable): Exception
}