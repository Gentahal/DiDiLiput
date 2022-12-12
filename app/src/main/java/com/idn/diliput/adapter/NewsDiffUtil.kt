package com.idn.diliput.adapter

import androidx.recyclerview.widget.DiffUtil
import com.idn.diliput.response.ArticlesItem

class NewsDiffUtil(private val oldList: ArrayList<ArticlesItem>, private val newList: List<ArticlesItem>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldData = oldList[oldItemPosition]
        val newData = newList[newItemPosition]
        return oldData.title == newData.title

                && oldData.author == newData.author
                && oldData.source == newData.source
                && oldData.description == newData.description

    }
}
