package com.picturebook.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class SearchItemsData(
    @Expose
    val total: Int,
    @Expose
    val totalHits: Int,
    @Expose
    @SerializedName("hits")
    val itemsList: List<ItemData>
)

data class FetchImageData(val page: Int, val itemsList: List<ItemData>?)

@Parcelize
data class ItemData(
    @Expose val id: Int = 0,
    @Expose val pageURL: String = "",
    @Expose val type: String = "",
    @Expose val tags: String = "",
    @Expose val previewURL: String = "",
    @Expose val previewWidth: Int = 0,
    @Expose val previewHeight: Int = 0,
    @Expose val webformatURL: String = "",
    @Expose val webformatWidth: Int = 0,
    @Expose val webformatHeight: Int = 0,
    @Expose val largeImageURL: String = "",
    @Expose val fullHDURL: String = "",
    @Expose val imageURL: String = "",
    @Expose val imageWidth: Int = 0,
    @Expose val imageHeight: Int = 0,
    @Expose val imageSize: Int = 0,
    @Expose val views: Int = 0,
    @Expose val downloads: Int = 0,
    @Expose val likes: Int = 0,
    @Expose val comments: Int = 0,
    @Expose val user_id: Int = 0,
    @Expose val user: String = "",
    @Expose val userImageURL: String = ""
) : Parcelable