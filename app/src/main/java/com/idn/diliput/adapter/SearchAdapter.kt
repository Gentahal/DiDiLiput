package com.idn.diliput.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idn.diliput.R
import com.idn.diliput.databinding.ItemNewsBinding
import com.idn.diliput.response.ResultsItem
import com.idn.diliput.ui.activity.DetailActivity
import com.squareup.picasso.Picasso

class SearchAdapter(private val listNews: ArrayList<ResultsItem>) :
    RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            if (listNews[position].creator == null) {
                itemSource.text = "unknown"
            } else {
                itemSource.text = listNews[position].creator.toString()
            }
            itemTitle.text = listNews[position].title

            if (listNews[position].imageUrl == null) {
                itemImg.setImageResource(R.drawable.example)
            } else {
                Picasso.get().load(listNews[position].imageUrl).into(itemImg)
            }
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.NEWS_DATA, listNews[position])
            it.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = listNews.size
}