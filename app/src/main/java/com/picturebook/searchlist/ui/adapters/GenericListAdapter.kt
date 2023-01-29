package com.picturebook.searchlist.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.search_item_card_view.view.*
import androidx.databinding.library.baseAdapters.BR

typealias OnItemClick<T> = (item: T, position: Int) -> Unit

class GenericListAdapter<T>(
    @IdRes private val layoutId: Int,
    private val clickListener: OnItemClick<T>
) : RecyclerView.Adapter<GenericListAdapter.ListItemHolder<T>>() {

    var imagesLst: ArrayList<T> = ArrayList()
        set(value) {
            field = value
            val diffCallback =
                ChildViewsDifferentiator(filteredImageLst, imagesLst)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            diffResult.dispatchUpdatesTo(this)
            if (filteredImageLst.size > 0)
                filteredImageLst.clear()
            filteredImageLst.addAll(value)
        }

    private var filteredImageLst: ArrayList<T> = ArrayList()
        set(value) {
            field = value
            val diffCallback =
                ChildViewsDifferentiator(filteredImageLst, imagesLst)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            diffResult.dispatchUpdatesTo(this)
        }

    fun clearProductItem() {
        imagesLst.clear()
    }

    class ListItemHolder<T>(private val lstViewDataBinder: ViewDataBinding) :
        RecyclerView.ViewHolder(lstViewDataBinder.root) {
        fun bind(itemData: T) {
            lstViewDataBinder.apply {
                setVariable(BR.lstItem, itemData)
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val lstItemLayoutBinder =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return ListItemHolder(lstItemLayoutBinder)
    }

    override fun onBindViewHolder(holder: ListItemHolder<T>, position: Int) {
        val lstItem = filteredImageLst[position]
        holder.apply {
            bind(lstItem)
            itemView.lstItemParentLayout.setOnClickListener {
                clickListener(lstItem, position)
            }
        }

    }

    override fun getItemCount(): Int = filteredImageLst.count()

    override fun getItemViewType(position: Int): Int = layoutId

    private class ChildViewsDifferentiator<T>(
        private val oldImageLst: ArrayList<T>,
        private val newImageLst: ArrayList<T>
    ) :
        DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldImageLst[oldItemPosition].toString() == newImageLst[newItemPosition].toString()
        }

        override fun getOldListSize(): Int {
            return oldImageLst.size
        }

        override fun getNewListSize(): Int {
            return newImageLst.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldImageLst[oldItemPosition] == newImageLst[newItemPosition]
        }
    }
}