package com.picturebook.moredetails.viewmodel

import androidx.lifecycle.*
import com.picturebook.model.ItemData
import com.picturebook.model.ResponseStatusCallbacks
import com.picturebook.moredetails.model.ImageDetails
import com.picturebook.moredetails.usecases.MoreDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreDetailsViewModel @Inject constructor(
    private val moreDetailsUseCase: MoreDetailsUseCase
) : ViewModel() {

    private val _selectedImageDetails = MutableLiveData<ResponseStatusCallbacks<ImageDetails>>()
    val selectedImageDetailsResponse: LiveData<ResponseStatusCallbacks<ImageDetails>>
        get() = _selectedImageDetails

    fun getSelectedImageDetails(selectedImage: ItemData) {
        _selectedImageDetails.value = ResponseStatusCallbacks.loading(null)
        viewModelScope.launch {
            try {
                _selectedImageDetails.value = ResponseStatusCallbacks.success(
                    data = moreDetailsUseCase.getRequiredImageDetails(selectedImage),
                    "Image details received."
                )
            } catch (e: Exception) {
                _selectedImageDetails.value =
                    ResponseStatusCallbacks.error(null, e.message.toString())
            }
        }
    }
}