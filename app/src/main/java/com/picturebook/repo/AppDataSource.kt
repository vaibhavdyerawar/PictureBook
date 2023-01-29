package com.picturebook.repo

import com.picturebook.model.SearchItemsData
import kotlinx.coroutines.flow.Flow

interface AppDataSource {

    suspend fun getSearchedImages(
        page: Int,
        search: String
    ): Flow<SearchItemsData>
}