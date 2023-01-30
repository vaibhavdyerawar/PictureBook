package com.picturebook.moredetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.picturebook.databinding.FragmentMoreDetailsBinding
import com.picturebook.model.ItemData
import com.picturebook.moredetails.viewmodel.MoreDetailsViewModel
import com.picturebook.utils.enums.ResponseStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoreDetailsFragment :  Fragment() {

    private val viewModel by  viewModels<MoreDetailsViewModel>()
    private lateinit var moreDetailsFragmentViewBinder: FragmentMoreDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMoreDetailsBinding.inflate(inflater, container, false).apply {
            moreDetailsFragmentViewBinder = this
        }.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSelectedImageDetails()
    }

    private fun getSelectedImageDetails() {
        val bundle = arguments ?: Bundle()
        viewModel.getSelectedImageDetails(
            MoreDetailsFragmentArgs.fromBundle(bundle).selImageItem ?: ItemData()
        )
        viewModel.selectedImageDetailsResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    it.data?.let { item ->
                        moreDetailsFragmentViewBinder.setVariable(BR.selectedImgItemData, item)
                        lifecycleScope.launch {
                            delay(500)
                            moreDetailsFragmentViewBinder.productImg.visibility = View.VISIBLE
                        }
                    }
                }
                ResponseStatus.ERROR -> {
                    //Nothing to do.
                }
                ResponseStatus.LOADING -> {
                    //Nothing to do.
                }
            }

        }
    }
}