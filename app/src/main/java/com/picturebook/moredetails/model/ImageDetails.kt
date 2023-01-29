package com.picturebook.moredetails.model


data class ImageDetails(
    val tags: String,
    val largeImageURL: String,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val user: String,
)