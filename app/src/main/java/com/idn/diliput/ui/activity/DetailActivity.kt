package com.idn.diliput.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.appbar.MaterialToolbar
import com.idn.diliput.R
import com.idn.diliput.databinding.ActivityDetailBinding
import com.idn.diliput.response.ArticlesItem
import com.idn.diliput.response.ResultsItem
import com.idn.diliput.response.SearchDataResponse
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(binding.root)


        var data = intent.getParcelableExtra<ResultsItem>(NEWS_DATA)
        data?.let {
            binding.apply {
                if (data.creator == null) {
                    detailSource.text = "unknown"
                } else {
                    detailSource.text = data.sourceId
                }

                if (data.creator == null) {
                    detailAuthor.text = "unknown"
                } else {
                    detailAuthor.text = data.creator
                }

                detailTitle.text = data.title
                detailContent.text = "${data.description} ${data.content}"

                Picasso.get().load(data.imageUrl).into(imgDetail)
            }
        }
    }

    private fun bookmark() {

    }

    companion object {
        var NEWS_DATA = "DATA"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}