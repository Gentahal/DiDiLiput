package com.idn.diliput.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.google.android.material.tabs.TabLayoutMediator
import com.idn.diliput.adapter.ViewPagerAdapter
import com.idn.diliput.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding
    private lateinit var viewPagerAdapter : ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapter = ViewPagerAdapter(this)
        

        with(binding){
            viewPager.adapter = viewPagerAdapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when(position) {
                    0 -> tab.text = "All"
                    1 -> tab.text = "Sports"
                    2 -> tab.text = "Bussiness"
                    3 -> tab.text = "Health"
                    4 -> tab.text = "Technology"
                    5 -> tab.text = "Science"
                    6 -> tab.text = "Entertainment"
                }
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}