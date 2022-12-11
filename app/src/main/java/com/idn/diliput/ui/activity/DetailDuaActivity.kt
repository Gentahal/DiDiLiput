package com.idn.diliput.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.idn.diliput.databinding.ActivityDetailDuaBinding
import com.idn.diliput.response.ArticlesItem
import com.squareup.picasso.Picasso

class DetailDuaActivity : AppCompatActivity() {

    private var _binding : ActivityDetailDuaBinding? = null
    private val binding get() = _binding as ActivityDetailDuaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailDuaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var news = intent.getParcelableExtra<ArticlesItem>(TAB_DATA)
        news?.let {
            binding.apply {
                detailSource.text = news.source?.name
                detailAuthor.text = news.content
                detailTitle.text = news.title
                detailContent.text = news.content


                Picasso.get().load(news.urlToImage).into(imgDetail)
            }
        }
    }

    companion object{
        var TAB_DATA = "TAB"
    }
}