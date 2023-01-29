package com.picturebook.utils.extensions

import java.util.*

fun String.shortStringLength(): String {
    var calStr = this
    if (this.length > 15)
        calStr = this.substring(0, 15).plus("...")
    return calStr
}

fun String.convertStringToUpperCase(): String {
    val calStr = this.split(" ").map { it.toLowerCase(Locale.ENGLISH).capitalize(Locale.ENGLISH) }
    return calStr.joinToString(separator = " ")
}