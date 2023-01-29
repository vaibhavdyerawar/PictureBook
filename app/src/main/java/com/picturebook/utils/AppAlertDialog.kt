package com.picturebook.utils

import android.app.Activity
import android.content.DialogInterface
import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.picturebook.R

class AppAlertDialog(
    private val activity: Activity,
    private val isCancelable: Boolean = true
) {

    private val materialAlertDialogBuilder: MaterialAlertDialogBuilder
    lateinit var onPositiveBtnClickListener: () -> Unit
    lateinit var onNegativeBtnClickListener: () -> Unit
    private val appAlertDialog: AlertDialog

    init {
        activity.let {
            materialAlertDialogBuilder = MaterialAlertDialogBuilder(it)
            appAlertDialog = materialAlertDialogBuilder.apply {
                setPositiveButton(R.string.lbl_OK,
                    DialogInterface.OnClickListener { dialog, id ->
                        onPositiveBtnClickListener()
                    })
                setNegativeButton(R.string.lbl_Cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        onNegativeBtnClickListener()

                    })
                setCancelable(isCancelable)
            }.create()
        }
    }

    fun setMessage(message: String) = apply {
        appAlertDialog.setMessage(message)
    }

    fun setTitle(title: String) = apply {
        appAlertDialog.setTitle(title)
    }

    fun setIcon(iconDrawable: Drawable) = apply {
        appAlertDialog.setIcon(iconDrawable)
    }

    fun show() {
        appAlertDialog.show()
    }

    fun dismissDialog() {
        appAlertDialog.dismiss()
    }
}