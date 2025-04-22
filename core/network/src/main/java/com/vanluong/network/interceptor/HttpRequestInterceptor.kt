package com.vanluong.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by van.luong
 * on 18,April,2025
 *
 * This class is responsible for intercepting HTTP requests and adding headers.
 */
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