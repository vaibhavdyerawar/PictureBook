package com.picturebook.di.modules

import com.picturebook.moredetails.usecases.MoreDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideMoreDetailsUseCaseInstance(): MoreDetailsUseCase = MoreDetailsUseCase()
}
