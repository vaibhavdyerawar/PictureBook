package com.picturebook.service

import android.content.Context
import com.picturebook.utils.extensions.isInternetAvailable
import okhttp3.Interceptor
import okhttp3.Response

class LocalCacheInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        if (!context.isInternetAvailable()) {
            requestBuilder.apply {
                removeHeader("Pragma")
                header("Cache-Control", "public, only-if-cached")
            }
        }
        return chain.proceed(requestBuilder.build())
    }
}
