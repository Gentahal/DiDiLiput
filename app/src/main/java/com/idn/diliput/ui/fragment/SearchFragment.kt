package com.idn.diliput.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.idn.diliput.adapter.SearchAdapter
import com.idn.diliput.databinding.FragmentSearchBinding
import com.idn.diliput.response.ResultsItem
import com.idn.diliput.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding as FragmentSearchBinding

    private var _viewModel: SearchViewModel? = null
    private val viewModel get() = _viewModel as SearchViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getDataSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText.let {
                    viewModel.apply {
                        getDataSearch(newText)
                        dataResponse.observe(viewLifecycleOwner) {
                            showData(it as ArrayList<ResultsItem>)
                        }
                        isLoading.observe(viewLifecycleOwner) { showLoading(it) }
                        isError.observe(viewLifecycleOwner) { showError(it) }
                    }
                }
                return false
            }
        })
    }


    private fun showLoading(isLoading : Boolean){
        binding.apply {
            icLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
            rvSearch.visibility = if (isLoading) View.GONE else View.VISIBLE
        }
    }

    private fun showError(error: Throwable?) {
        Log.e("SearchFragment", "showError: $error")
    }

    private fun showData(data: ArrayList<ResultsItem>) {
        binding.rvSearch.apply {
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
            Log.i("showData", "showData: $data")
            adapter = SearchAdapter(data)
        }
    }

}

