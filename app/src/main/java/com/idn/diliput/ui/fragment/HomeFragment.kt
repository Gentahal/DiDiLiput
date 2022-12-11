package com.idn.diliput.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.MenuItemCompat
import androidx.core.view.get
import androidx.lifecycle.Lifecycle
import com.google.android.material.tabs.TabLayoutMediator
import com.idn.diliput.R
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
                    0 -> tab.text = "Semua"
                    1 -> tab.text = "Olahraga"
                    2 -> tab.text = "Bisinis"
                    3 -> tab.text = "Kesehatan"
                    4 -> tab.text = "Teknologi"
                    5 -> tab.text = "Sains"
                    6 -> tab.text = "Hiburan"
                }
            }.attach()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}