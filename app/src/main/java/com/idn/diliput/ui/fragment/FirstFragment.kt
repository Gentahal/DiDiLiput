package com.idn.diliput.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.idn.diliput.R
import com.idn.diliput.adapter.NewsAdapter
import com.idn.diliput.databinding.FragmentFirstBinding
import com.idn.diliput.response.ArticlesItem
import com.idn.diliput.viewmodel.TabBarViewModel

class FirstFragment(val category: String) : Fragment() {

    private var _binding : FragmentFirstBinding? = null
    private val binding get() = _binding as FragmentFirstBinding

    private var _viewModel : TabBarViewModel? = null
    private val viewModel get() = _viewModel as TabBarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewModel = ViewModelProvider(this).get(TabBarViewModel::class.java)
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            getHeadlineNews(category)
            listData.observe(viewLifecycleOwner) {showHeadline(it as ArrayList<ArticlesItem>)}
            isError.observe(viewLifecycleOwner) {showError(it)}
        }
    }




    private fun showError(error: Throwable?) {
        Log.e("SearchFragment", "showError: $error")
    }

    private fun showHeadline(data: ArrayList<ArticlesItem>){
        binding.rvFirst.apply {
            layoutManager = GridLayoutManager(context,1, GridLayoutManager.VERTICAL, false)
            adapter = NewsAdapter(data)
        }
    }

}