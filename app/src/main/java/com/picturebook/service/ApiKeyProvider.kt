package com.picturebook.service

object ApiKeyProvider {

    init {
        System.loadLibrary("app_native_lib")
    }

    external fun getAPIKey(): String
}