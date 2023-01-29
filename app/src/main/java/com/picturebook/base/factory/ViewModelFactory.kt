package com.picturebook.base.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.picturebook.moredetails.usecases.MoreDetailsUseCase
import com.picturebook.moredetails.viewmodel.MoreDetailsViewModel
import com.picturebook.repo.AppDataSource
import com.picturebook.searchlist.usecases.SearchUseCase
import com.picturebook.searchlist.viewmodel.SearchListViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(private val repository: AppDataSource) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SearchListViewModel::class.java) ->
                SearchListViewModel(SearchUseCase(repository)) as T
            else -> throw IllegalArgumentException("Unknown viewModel class $modelClass")
        }
    }
}