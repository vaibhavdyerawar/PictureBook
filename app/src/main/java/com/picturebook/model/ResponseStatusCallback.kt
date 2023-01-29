package com.picturebook.model

import com.picturebook.utils.enums.ResponseStatus

data class ResponseStatusCallbacks<out T>(
    val status: ResponseStatus,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T, message: String? = null): ResponseStatusCallbacks<T> =
            ResponseStatusCallbacks(status = ResponseStatus.SUCCESS, data = data, message = message)

        fun <T> error(data: T?, message: String): ResponseStatusCallbacks<T> =
            ResponseStatusCallbacks(status = ResponseStatus.ERROR, data = data, message = message)

        fun <T> loading(data: T?): ResponseStatusCallbacks<T> =
            ResponseStatusCallbacks(status = ResponseStatus.LOADING, data = data, message = null)
    }
}