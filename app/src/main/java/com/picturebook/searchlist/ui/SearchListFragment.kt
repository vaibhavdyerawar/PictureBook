package com.picturebook.searchlist.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kennyc.view.MultiStateView
import com.picturebook.R
import com.picturebook.databinding.FragmentSearchListBinding
import com.picturebook.model.ItemData
import com.picturebook.searchlist.ui.adapters.GenericListAdapter
import com.picturebook.searchlist.viewmodel.SearchListViewModel
import com.picturebook.utils.AppAlertDialog
import com.picturebook.utils.enums.ResponseStatus
import com.picturebook.utils.extensions.hideKeyboard
import com.picturebook.utils.extensions.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import org.apache.commons.lang3.StringUtils
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class SearchListFragment : Fragment() {

    private val viewModel by viewModels<SearchListViewModel>()
    private lateinit var adapter: GenericListAdapter<ItemData>
    private lateinit var searchFragmentViewBinder: FragmentSearchListBinding
    private lateinit var navDirection: NavDirections
    private lateinit var appAlertDialog: AppAlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSearchListBinding.inflate(inflater, container, false).apply {
            searchFragmentViewBinder = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initSearchListRcView()
        fetchImageList()
        setUpLoadMoreAction()
        setUpSearchBarActions()
        setUpMoreDetailsAlertDialog()
    }

    @SuppressLint("ResourceType")
    private fun initSearchListRcView() {
        adapter = GenericListAdapter(R.layout.search_item_card_view) { item, position ->
            navDirection =
                SearchListFragmentDirections.actionSearchListFragmentToMoreDetailFragment(item)
            appAlertDialog.show()
        }
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        searchFragmentViewBinder.rcViewSearchList.adapter = adapter
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setUpMoreDetailsAlertDialog() {
        appAlertDialog = AppAlertDialog(requireActivity()).apply {
            setMessage(getString(R.string.lbl_msg_see_more_details))
            setTitle(getString(R.string.lbl_title_see_more_details))
            onPositiveBtnClickListener = {
                findNavController().navigate(navDirection)
            }
            onNegativeBtnClickListener = {
                dismissDialog()
            }
        }
    }

    private fun fetchImageList() {
        viewModel.searchResponse.observe(viewLifecycleOwner) {
            when (it.status) {
                ResponseStatus.SUCCESS -> {
                    it.data?.apply {
                        adapter.imagesLst = itemsList as ArrayList<ItemData>
                        searchFragmentViewBinder.multiStateView.viewState =
                            MultiStateView.ViewState.CONTENT
                    }
                }
                ResponseStatus.ERROR -> {
                    it.data?.let { item ->
                        if (item.page == 1) {
                            searchFragmentViewBinder.multiStateView.viewState =
                                MultiStateView.ViewState.EMPTY
                        } else
                            searchFragmentViewBinder.nestedScrollView.showSnackBar(
                                message = it.message.toString()
                            )
                    } ?: run {
                        searchFragmentViewBinder.multiStateView.viewState =
                            MultiStateView.ViewState.ERROR
                        searchFragmentViewBinder.nestedScrollView.showSnackBar(
                            message = getString(R.string.msg_no_internet_connection),
                            action = getString(R.string.lbl_retry)
                        ) {
                            viewModel.retry()
                        }
                    }
                }
                ResponseStatus.LOADING -> {
                    it.data?.let { item ->
                        if (item.page == 1) searchFragmentViewBinder.multiStateView.viewState =
                            MultiStateView.ViewState.LOADING
                        else
                            searchFragmentViewBinder.nestedScrollView.showSnackBar(
                                message = getString(R.string.msg_loading_more_imgs)
                            )
                    }
                }
            }
        }
    }

    private fun setUpLoadMoreAction() {
        searchFragmentViewBinder.nestedScrollView.setOnScrollChangeListener { view: NestedScrollView, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == view.getChildAt(0).measuredHeight - view.measuredHeight) {
                viewModel.loadNextPagePhotos()
            }
        }
    }

    private fun setUpSearchBarActions() {
        searchFragmentViewBinder.edtSearchPhotos.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchFragmentViewBinder.edtSearchPhotos.hideKeyboard()
                val queryStr = searchFragmentViewBinder.edtSearchPhotos.text.toString()
                adapter.clearProductItem()
                searchFragmentViewBinder.txtViewPopulateTxt.text =
                    "${getString(R.string.lbl_search_token)} ${queryStr.toUpperCase(Locale.ENGLISH)}"
                viewModel.searchImagesFromServer(queryStr)
            }
            false
        }

        searchFragmentViewBinder.inputTxtSearchBar.setEndIconOnClickListener {
            searchFragmentViewBinder.edtSearchPhotos.setText(StringUtils.EMPTY)
            adapter.clearProductItem()
            searchFragmentViewBinder.txtViewPopulateTxt.text = getString(R.string.lbl_search_latest)
            viewModel.searchImagesFromServer("fruits")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

 /*   override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_menu -> {
                // navigate to settings screen
                true
            }
            R.id.night_menu -> {
                // save profile changes
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.search_menu)
        item.isVisible = isEditing
    }*/

}