package com.dsm.api.interceptor

import com.dsm.api.TokenApi
import com.dsm.pref.PrefStorage
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection

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

        return if (response.code == HttpURLConnection.HTTP_FORBIDDEN) {
            val refreshResponse = tokenApi.refreshToken(refreshToken).execute()

            if (refreshResponse.code() == HttpURLConnection.HTTP_OK) {
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
            response
        }
    }

}