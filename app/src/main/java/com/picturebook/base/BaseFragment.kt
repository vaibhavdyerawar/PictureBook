package com.picturebook.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


abstract class BaseFragment :
    Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}