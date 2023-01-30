package com.picturebook.di.modules

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.picturebook.service.Api
import com.picturebook.repo.remote.ResponseInterceptorOnline
import com.picturebook.repo.local.LocalCacheInterceptor
import com.picturebook.utils.CONSTANTS.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun buildBackendApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        coroutineCallAdapterFactory: CoroutineCallAdapterFactory,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(coroutineCallAdapterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        responseInterceptorOnline: ResponseInterceptorOnline,
        localCacheInterceptor: LocalCacheInterceptor
    ): OkHttpClient {
        return OkHttpClient().newBuilder().also { item ->
            val log = HttpLoggingInterceptor()
            log.level = HttpLoggingInterceptor.Level.HEADERS
            item.addInterceptor(log)
            item.addInterceptor(localCacheInterceptor)
            item.addNetworkInterceptor(responseInterceptorOnline)
            item.retryOnConnectionFailure(true)
        }.build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideCoroutineCallAdapter(): CoroutineCallAdapterFactory {
        return CoroutineCallAdapterFactory.invoke()
    }

    @Provides
    @Singleton
    fun provideCacheInterceptor(): ResponseInterceptorOnline {
        return ResponseInterceptorOnline()
    }

    @Provides
    @Singleton
    fun provideLocalCacheInterceptor(@ApplicationContext context: Context): LocalCacheInterceptor {
        return LocalCacheInterceptor(context)
    }
}