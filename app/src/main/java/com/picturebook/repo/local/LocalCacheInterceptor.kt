package com.picturebook.repo.local

import android.content.Context
import com.picturebook.utils.extensions.isInternetAvailable
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

class LocalCacheInterceptor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        if (!context.isInternetAvailable()) {
            requestBuilder.cacheControl(CacheControl.FORCE_CACHE)
        }
        return chain.proceed(requestBuilder.build())
    }
}
