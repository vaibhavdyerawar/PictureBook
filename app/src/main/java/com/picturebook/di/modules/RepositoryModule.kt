package com.picturebook.di.modules

import com.picturebook.repo.AppDataSource
import com.picturebook.repo.network.NetworkDataRepo
import com.picturebook.service.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideNetworkDataSource(apiService: Api): AppDataSource {
        return NetworkDataRepo(apiService)
    }
}