package com.yatoooon.architecture.ui.adapter

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yatoooon.architecture.BR
import com.yatoooon.architecture.R
import com.yatoooon.architecture.data.model.Item
import com.yatoooon.architecture.databinding.LayoutItemBinding

class RepoAdapter : PagingDataAdapter<Item, RepoAdapter.BindingHolder>(COMPARATOR) {


    inner class BindingHolder(val binding: LayoutItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        return BindingHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_item,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.binding.setVariable(BR.item, item)
            holder.binding.executePendingBindings()
            holder.itemView.setOnClickListener {
                onClick(item)
            }
        }
    }

    var onClick: (Item) -> Unit = {}
    fun setOnClickListener(onClick: (Item) -> Unit) {
        this.onClick = onClick
    }


    //DiffUtil
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: Item, newItem: Item): Any? {
                val payload = Bundle()
                if (!TextUtils.equals(oldItem.name, newItem.name)) {
                    payload.putString("KEY_NAME", newItem.name)
                }
                if (!TextUtils.equals(oldItem.stargazers_count, newItem.stargazers_count)) {
                    payload.putString("KEY_STAR", newItem.stargazers_count)
                }
                if (!TextUtils.equals(oldItem.description, newItem.description)) {
                    payload.putString("KEY_DESC", newItem.description)
                }
                if (payload.size() == 0)//如果没有变化 就传空
                    return null
                return payload
            }
        }
    }

    override fun onBindViewHolder(
        holder: BindingHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            val any: Bundle = payloads[0] as Bundle
            for (key in any.keySet()) {
                when (key) {
                    "KEY_NAME" -> holder.binding.tvName.text = any.get(key).toString()
                    "KEY_STAR" -> holder.binding.tvStar.text = any.get(key).toString()
                    "KEY_DESC" -> holder.binding.tvDesc.text = any.get(key).toString()
                }
            }
        }
    }


}
