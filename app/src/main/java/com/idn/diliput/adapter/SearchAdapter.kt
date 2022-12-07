package com.idn.diliput.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idn.diliput.R
import com.idn.diliput.databinding.ItemNewsBinding
import com.idn.diliput.response.ArticlesItem
import com.idn.diliput.response.ResultsItem
import com.squareup.picasso.Picasso
import retrofit2.http.Url

class SearchAdapter(private val listNews: ArrayList<ResultsItem>) :
    RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            if (listNews[position].creator == null) {
                itemSource.text = "unknow"
            } else {
                itemSource.text = listNews[position].creator
            }
            itemTitle.text = listNews[position].title

            if (listNews[position].imageUrl == null) {
                itemImg.setImageResource(R.drawable.example)
            } else {
                Picasso.get().load(listNews[position].imageUrl).into(itemImg)
            }
        }

    }

    override fun getItemCount(): Int = listNews.size
}