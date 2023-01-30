package com.picturebook.repo.remote

import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptorOnline : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        return when (originalResponse.header("Cache-Control")) {
            null, "no-store",
            "no-cache",
            "must-revalidate",
            "max-age=0" -> {
                originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + 5000)
                    .build()
            }
            else -> originalResponse
        }
    }
}