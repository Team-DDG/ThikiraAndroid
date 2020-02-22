package com.dsm.restaurant.data.error

import com.dsm.restaurant.data.error.exception.*
import retrofit2.HttpException
import java.net.HttpURLConnection

interface ErrorHandler {

    fun getNetworkException(e: Exception): Exception
}

class ErrorHandlerImpl : ErrorHandler {

    override fun getNetworkException(e: Exception): Exception =
        when (e) {
            is HttpException -> {
                when (e.code()) {
                    HttpURLConnection.HTTP_UNAUTHORIZED -> UnauthorizedException(e)

                    HttpURLConnection.HTTP_FORBIDDEN -> ForbiddenException(e)

                    HttpURLConnection.HTTP_NOT_FOUND -> NotFoundException(e)

                    HttpURLConnection.HTTP_CONFLICT -> ConflictException(e)

                    else -> InternalException(e)
                }
            }
            else -> InternalException(e)
        }

}