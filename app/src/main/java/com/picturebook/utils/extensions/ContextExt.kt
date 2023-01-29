package com.picturebook.utils.extensions

import android.content.Context
import android.net.ConnectivityManager

fun Context.isInternetAvailable(): Boolean {
    val connManager: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
    return connManager.activeNetworkInfo?.isConnected ?: false
}