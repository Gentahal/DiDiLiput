package com.idn.diliput.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.idn.diliput.R
import com.idn.diliput.adapter.SearchAdapter
import com.idn.diliput.databinding.FragmentSearchBinding
import com.idn.diliput.databinding.FragmentSecondBinding
import com.idn.diliput.response.ArticlesItem
import com.idn.diliput.response.ResultsItem
import com.idn.diliput.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private var _binding : FragmentSearchBinding? = null
    private val binding get() = _binding as FragmentSearchBinding

    private var _viewModel : SearchViewModel? = null
    private val viewModel get() = _viewModel as SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun showData(data: ArrayList<ResultsItem>) {
        binding.rvSearch.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = SearchAdapter(data)
        }
    }

}

