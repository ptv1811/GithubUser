package com.vanluong.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .url(originalRequest.url)
            .build()
        return chain.proceed(request)
    }
}