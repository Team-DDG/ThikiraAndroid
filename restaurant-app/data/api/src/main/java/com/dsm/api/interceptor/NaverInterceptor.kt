package com.dsm.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class NaverInterceptor(
    private val naverClientId: String,
    private val naverClientSecret: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", naverClientId)
                .addHeader("X-Naver-Client-Secret", naverClientSecret)
                .build()
        )
    }
}