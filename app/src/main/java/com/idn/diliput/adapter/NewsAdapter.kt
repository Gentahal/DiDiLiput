package com.idn.diliput.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.idn.diliput.databinding.ItemNewsBinding
import com.idn.diliput.response.ArticlesItem
import com.idn.diliput.ui.activity.DetailActivity
import com.idn.diliput.ui.activity.DetailDuaActivity
import com.squareup.picasso.Picasso

class NewsAdapter :
    RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    private val listNews: ArrayList<ArticlesItem> = arrayListOf()

    class MyViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            itemSource.text = listNews[position].author
            itemTitle.text = listNews[position].title

            Picasso.get().load(listNews[position].urlToImage).into(itemImg)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context,DetailDuaActivity::class.java)
            intent.putExtra(DetailDuaActivity.TAB_DATA, listNews[position])
            it.context.startActivity(intent)
        }

    }

    fun getNewsAt(position: Int) = listNews[position]


    override fun getItemCount(): Int = listNews.size

    fun setData(list: List<ArticlesItem>) {
        val alarmDiffUtil = NewsDiffUtil(listNews,list)
        val alarmDiffUtilResult = DiffUtil.calculateDiff(alarmDiffUtil)
        listNews.clear()
        listNews.addAll(list)
        alarmDiffUtilResult.dispatchUpdatesTo(this)
    }
}