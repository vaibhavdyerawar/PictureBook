package com.picturebook.service

import com.picturebook.model.SearchItemsData
import com.picturebook.service.EndPoints.GET_IMAGES_DATA
import com.picturebook.utils.CONSTANTS
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET(GET_IMAGES_DATA)
    suspend fun getSearchResultData(
        @Query(CONSTANTS.KEY) key: String,
        @Query(CONSTANTS.PAGE) page: Int = 1,
        @Query(CONSTANTS.SEARCH) search: String
    ): SearchItemsData

}