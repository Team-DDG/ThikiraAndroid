package com.dsm.restaurant.data.remote.interceptor

import com.dsm.restaurant.data.local.PrefStorage
import com.dsm.restaurant.data.remote.TokenApi
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val prefStorage: PrefStorage,
    private val tokenApi: TokenApi
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = prefStorage.getAccessToken()
        val refreshToken = prefStorage.getRefreshToken()

        val request = chain.request().newBuilder().run {
            removeHeader("Authorization")
            addHeader("Authorization", accessToken)
            build()
        }

        val response = chain.proceed(request)

        return if (response.code == 403) {
            val refreshResponse = tokenApi.refreshToken(refreshToken).execute()
            if (refreshResponse.code() == 200) {
                val newToken = refreshResponse.body()?.get("accessToken") ?: ""
                prefStorage.setAccessToken(newToken)

                response.close()
                chain.proceed(
                    request.newBuilder().run {
                        removeHeader("Authorization")
                        addHeader("Authorization", newToken)
                        build()
                    }
                )
            } else {
                prefStorage.deleteAccessToken()
                prefStorage.deleteRefreshToken()
                response
            }
        } else {
            return response
        }
    }

}