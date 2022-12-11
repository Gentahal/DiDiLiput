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
    private val viewModel get() = _viewmodel as TabBarViewModel

    private var _data: ArticlesItem? = null
    private val data get() = _data as ArticlesItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _data = intent.getParcelableExtra(TAB_DATA)
        _viewmodel = ViewModelProvider(this)[TabBarViewModel::class.java]
        _binding = ActivityDetailDuaBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbarDetailDua)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
        viewModel.isNewsBookmarked(data).observe(this@DetailDuaActivity) { isBookmarked ->
            binding.saveButton.apply {
                if (isBookmarked) {
                    setText(R.string.delete)
                } else {
                    setText(R.string.save)
                }

                var message: String
                setOnClickListener {
                    message = if (isBookmarked) {
                        viewModel.unBookmarkNews(data)
                        context.getString(R.string.txt_bookmark_removed)

                    } else {
                        viewModel.bookmarkNews(data)
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
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}