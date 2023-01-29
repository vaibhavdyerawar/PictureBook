package com.picturebook.service

object ApiKeyProvider {

    init {
        System.loadLibrary("native-lib")
    }

    external fun getAPIKey(): String
}