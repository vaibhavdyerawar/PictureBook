package com.picturebook.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.picturebook.R

object BindingAdapter : BaseObservable() {

    @JvmStatic
    @BindingAdapter("loadPreviewImage")
    fun onLoadPreviewImages(imageView: AppCompatImageView, imgURL: String?) {
        val circleProgress = CircularProgressDrawable(imageView.context)
        circleProgress.strokeWidth = 5f
        circleProgress.centerRadius = 40f
        circleProgress.start()

        Glide.with(imageView.context)
            .asBitmap()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .load(imgURL)
            .placeholder(circleProgress)
            .error(R.drawable.loading_placeholder)
            .into(imageView)

    }

    @JvmStatic
    @BindingAdapter("loadLargeImage")
    fun onLoadLargeImages(imageView: AppCompatImageView, imgURL: String?) {
        val circleProgress = CircularProgressDrawable(imageView.context)
        circleProgress.strokeWidth = 5f
        circleProgress.centerRadius = 40f
        circleProgress.start()

        Glide.with(imageView.context)
            .asBitmap()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .load(imgURL)
            .placeholder(circleProgress)
            .error(R.drawable.loading_placeholder)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("setDrawablePadding")
    fun setDrawablePadding(txt: AppCompatTextView, padding: Float = 0f) {
        txt.compoundDrawablePadding = padding.toInt()
    }

    @JvmStatic
    @BindingAdapter("loadIntAsString")
    fun loadIntAsString(txt: AppCompatTextView, name: Int?) {
        txt.text = name?.toString()
    }
}