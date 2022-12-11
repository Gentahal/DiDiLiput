package com.idn.diliput.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import com.idn.diliput.R
import com.idn.diliput.adapter.NewsAdapter
import com.idn.diliput.databinding.FragmentBookmarkBinding
import com.idn.diliput.response.ArticlesItem
import com.idn.diliput.viewmodel.TabBarViewModel

class BookmarkFragment : Fragment() {

    private var _binding : FragmentBookmarkBinding? = null
    private val binding get() = _binding as FragmentBookmarkBinding

    private var _viewmodel : TabBarViewModel? = null
    private val viewModel get() = _viewmodel as TabBarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _viewmodel = ViewModelProvider(this)[TabBarViewModel::class.java]
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showData()

    }

    private fun showData() {
        viewModel.apply {
            getAllBookmarkedNews().observe(viewLifecycleOwner) { data ->
                data?.let {
                    binding.rvBookmark.apply {
                        adapter = NewsAdapter(data as ArrayList<ArticlesItem>)
                        layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
                    }
                }
            }
        }
    }


}