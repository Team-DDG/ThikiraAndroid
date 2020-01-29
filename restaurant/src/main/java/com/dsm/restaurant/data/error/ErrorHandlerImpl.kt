package com.dsm.restaurant.data.error

import com.dsm.restaurant.data.error.exception.ConflictException
import com.dsm.restaurant.data.error.exception.InternalException
import com.dsm.restaurant.data.error.exception.NotFoundException
import retrofit2.HttpException
import java.net.HttpURLConnection

class ErrorHandlerImpl : ErrorHandler {

    override fun getNetworkException(e: Exception): Exception =
        when (e) {
            is HttpException -> {
                when (e.code()) {
                    HttpURLConnection.HTTP_CONFLICT -> ConflictException(e)

                    HttpURLConnection.HTTP_NOT_FOUND -> NotFoundException(e)

                    else -> InternalException(e)
                }
            }
            else -> InternalException(e)
        }

}