package com.picturebook.di.modules

import com.picturebook.moredetails.ui.MoreDetailsFragment
import com.picturebook.searchlist.ui.SearchListFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object UIModule {

    @Provides
    @Singleton
    fun provideSearchListFragment(): SearchListFragment =
        SearchListFragment()

    @Provides
    @Singleton
    fun provideMoreDetailFragment(): MoreDetailsFragment =
        MoreDetailsFragment()

}