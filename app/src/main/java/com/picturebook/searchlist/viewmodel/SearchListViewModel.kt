package com.picturebook.searchlist.viewmodel

import androidx.lifecycle.*
import com.picturebook.model.FetchImageData
import com.picturebook.model.ItemData
import com.picturebook.model.ResponseStatusCallbacks
import com.picturebook.searchlist.usecases.SearchUseCase
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.apache.commons.lang3.StringUtils
import javax.inject.Inject

@HiltViewModel
class SearchListViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _searchResultList = MutableLiveData<ResponseStatusCallbacks<FetchImageData>>()
    val searchResponse: LiveData<ResponseStatusCallbacks<FetchImageData>>
        get() = _searchResultList

    private var pagination = 1
    private var searchString = StringUtils.EMPTY
    private var updatedItems = arrayListOf<ItemData>()

    fun searchImagesFromServer(search: String) {
        pagination
        searchString = search
        getImagesWithSearchQuery(pagination, searchString)
    }

    fun loadNextPagePhotos() {
        pagination++
        getImagesWithSearchQuery(pagination, searchString)
    }

    fun retry() {
        getImagesWithSearchQuery(pagination, searchString)
    }

    private fun getImagesWithSearchQuery(pagination: Int, queryStr: String) {
        _searchResultList.value = ResponseStatusCallbacks.loading(
            data = FetchImageData(page = pagination, itemsList = null)
        )

        viewModelScope.launch {
            try {
                searchUseCase(page = pagination, category = queryStr).collect { dataSet ->
                    dataSet.itemsList.let {
                        if (it.isNotEmpty()) {
                            if (pagination == 1) {
                                updatedItems = arrayListOf()
                                updatedItems.addAll(it)
                            } else {
                                updatedItems.addAll(it)
                            }
                            _searchResultList.postValue(
                                ResponseStatusCallbacks.success(
                                    data = FetchImageData(
                                        page = pagination,
                                        itemsList = updatedItems
                                    ),
                                    "List of images received"
                                )
                            )
                        } else {
                            _searchResultList.value = ResponseStatusCallbacks.error(
                                data = FetchImageData(
                                    page = pagination,
                                    itemsList = null
                                ),
                                if (pagination == 1) "Sorry, no relevant data found." else "Sorry, no more images available."
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _searchResultList.value =
                    ResponseStatusCallbacks.error(null, e.message.toString())
            }
        }

    }
}