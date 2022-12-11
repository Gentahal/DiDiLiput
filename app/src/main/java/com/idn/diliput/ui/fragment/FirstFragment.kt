package com.idn.diliput.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.idn.diliput.adapter.NewsAdapter
import com.idn.diliput.databinding.FragmentFirstBinding
import com.idn.diliput.response.ArticlesItem
import com.idn.diliput.ui.activity.DetailActivity
import com.idn.diliput.viewmodel.TabBarViewModel

class FirstFragment(private val category: String) : Fragment() {

    private var _binding : FragmentFirstBinding? = null
    private val binding get() = _binding as FragmentFirstBinding

    private var _viewModel : TabBarViewModel? = null
    private val viewModel get() = _viewModel as TabBarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewModel = ViewModelProvider(this)[TabBarViewModel::class.java]
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.rvFirst.setOnClickListener {
            activity?.let {
                startActivity(Intent(context, DetailActivity::class.java))
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        show()

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = true
            show()
        }
    }

    private fun show() {
        viewModel.apply {
            getHeadlineNews(category)
            listData.observe(viewLifecycleOwner) {
                binding.swipeRefresh.isRefreshing = false
                showHeadline(it as ArrayList<ArticlesItem>)
            }
            isError.observe(viewLifecycleOwner) {
                binding.swipeRefresh.isRefreshing = false
                showError(it)
            }


        }
    }

    private fun showError(error: Throwable?) {
        Log.e("SearchFragment", "showError: $error")
    }


    private fun showHeadline(data: ArrayList<ArticlesItem>) {
        binding.rvFirst.apply {
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
            adapter = NewsAdapter(data)
        }
    }

}