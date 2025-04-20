package com.vanluong.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by van.luong
 * on 20,April,2025
 */
open class BaseRecyclerViewAdapter<T : Any, VDB : ViewDataBinding>(
    @LayoutRes val layoutId: Int,
    var clickListener: (T, VDB) -> Unit = { _, _ ->
    },
    var onBindViewHolderCallback: (T, VDB, Int) -> Unit = { _, _, _ ->
    },
    var onCreateViewHolderCallback: (ViewGroup, Int, VDB) -> RecyclerViewHolder<VDB> = { _, _, binding ->
        RecyclerViewHolder(binding)
    }
) : RecyclerView.Adapter<BaseRecyclerViewAdapter.RecyclerViewHolder<VDB>>() {

    private val items: MutableList<T> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder<VDB> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<VDB>(
            inflater, layoutId, parent, false
        )
        return onCreateViewHolderCallback(parent, viewType, binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun removeItem(item: T) {
        val position = this.items.indexOf(item)
        this.items.remove(item)
        notifyItemRemoved(position)
    }

    fun getItems() = items

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerViewHolder<VDB>, position: Int) {
        val itemAtPos = items[position]
        val itemViewType = getItemViewType(position)
        holder.binding.apply {
            onBindViewHolderCallback(itemAtPos, this, itemViewType)
        }

        holder.binding.apply {
            holder.itemView.setOnClickListener {
                clickListener(itemAtPos, this)
            }
        }
    }

    class RecyclerViewHolder<VDB : ViewDataBinding>(val binding: VDB) :
        RecyclerView.ViewHolder(binding.root)
}