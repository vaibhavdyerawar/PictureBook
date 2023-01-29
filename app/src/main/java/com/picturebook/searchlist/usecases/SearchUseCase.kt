package com.picturebook.searchlist.usecases

import com.picturebook.repo.AppDataSource
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: AppDataSource) {
    suspend operator fun invoke(
        page: Int = 1,
        category: String
    ) = repository.getSearchedImages(
        page = page,
        search = category
    )
}