package com.picturebook.utils

import android.view.Gravity
import android.view.View
import androidx.annotation.Dimension
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.picturebook.R
import com.picturebook.utils.extensions.convertStringToUpperCase
import com.picturebook.utils.extensions.shortStringLength

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
    @BindingAdapter("loadShortString")
    fun loadShortString(txt: AppCompatTextView, name: String?) {
        txt.text = name?.shortStringLength()?.convertStringToUpperCase()
    }

    @JvmStatic
    @BindingAdapter("loadIntAsString")
    fun loadIntAsString(txt: AppCompatTextView, name: Int?) {
        txt.text = name?.toString()
    }

    @JvmStatic
    @BindingAdapter("setTextAlignmentCenter")
    fun setDrawablePaddingRight(txt: AppCompatTextView, alignCenter: Boolean) {
        if(alignCenter)
            txt.gravity = Gravity.CENTER_HORIZONTAL
    }
}