package com.idn.diliput.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.idn.diliput.R
import com.idn.diliput.databinding.ActivityDetailDuaBinding
import com.idn.diliput.response.ArticlesItem
import com.idn.diliput.viewmodel.TabBarViewModel
import com.squareup.picasso.Picasso

class DetailDuaActivity : AppCompatActivity() {

    private var _binding : ActivityDetailDuaBinding? = null
    private val binding get() = _binding as ActivityDetailDuaBinding

    private var _viewmodel : TabBarViewModel? = null
    private val viewmodel get() = _viewmodel as TabBarViewModel

    private var _data : ArticlesItem? = null
    private val data get() = _data as ArticlesItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _data = intent.getParcelableExtra(TAB_DATA)
        _viewmodel = ViewModelProvider(this)[TabBarViewModel::class.java]
        _binding = ActivityDetailDuaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data.let {
            binding.apply {
                detailSource.text = data.source?.name
                detailAuthor.text = data.author
                detailTitle.text = data.title
                detailContent.text = data.content


                Picasso.get().load(data.urlToImage).into(imgDetail)
            }
        }

        bookmark()

    }

    private fun bookmark() {
        viewmodel.isNewsBookmarked(data).observe(this@DetailDuaActivity) { isBookmarked ->
            binding.btnBookmark.apply {
                if (isBookmarked) {
                    setImageResource(R.drawable.ic_bookmark)
                } else {
                    setImageResource(R.drawable.ic_bookmark_border)
                }

                var message: String
                setOnClickListener {
                    message = if (isBookmarked) {
                        viewmodel.unBookmarkNews(data)
                        context.getString(R.string.txt_bookmark_removed)

                    } else {
                        viewmodel.bookmarkNews(data)
                        context.getString(R.string.txt_bookmark_added)
                    }
                    Toast.makeText(this@DetailDuaActivity, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object{
        var TAB_DATA = "TAB"
    }
}