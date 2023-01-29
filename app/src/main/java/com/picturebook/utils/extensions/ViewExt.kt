package com.picturebook.utils.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(message: String, action: String = "", actionListener: () -> Unit = {}): Snackbar {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    if (action != "") {
        snackBar.duration = Snackbar.LENGTH_INDEFINITE
        snackBar.setAction(action) {
            actionListener()
            snackBar.dismiss()
        }
    }
    snackBar.show()
    return snackBar
}

fun View.hideKeyboard() {
    val keyboard: InputMethodManager? = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    keyboard?.hideSoftInputFromWindow(windowToken, 0)
}