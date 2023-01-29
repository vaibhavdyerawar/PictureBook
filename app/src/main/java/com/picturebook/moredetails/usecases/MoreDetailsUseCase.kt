package com.picturebook.moredetails.usecases

import com.picturebook.model.ItemData
import com.picturebook.moredetails.model.ImageDetails

class MoreDetailsUseCase {
    fun getRequiredImageDetails(selectedImage: ItemData) = ImageDetails(
        tags = selectedImage.tags,
        largeImageURL = selectedImage.largeImageURL,
        downloads = selectedImage.downloads,
        likes = selectedImage.likes,
        comments = selectedImage.comments,
        user = selectedImage.user,
    )
}