package com.picturebook.repo.network

import com.picturebook.model.SearchItemsData
import com.picturebook.repo.AppDataSource
import com.picturebook.service.Api
import com.picturebook.service.ApiKeyProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkDataRepo @Inject constructor(private val apiService: Api) : AppDataSource {

    override suspend fun getSearchedImages(page: Int, search: String): Flow<SearchItemsData> {
        return flow {
            emit(
                apiService.getSearchResultData(
                    key = "33181627-dfe938147d37944eaf644206e", //ApiKeyProvider.getAPIKey(),
                    page = page,
                    search = search
                )
            )
        }
    }
}


