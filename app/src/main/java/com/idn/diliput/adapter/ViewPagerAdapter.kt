package com.idn.diliput.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.idn.diliput.ui.fragment.FirstFragment
import com.idn.diliput.ui.fragment.SecondFragment

class ViewPagerAdapter(fragment: Fragment)
    : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()
        when(position) {
            0 -> fragment = FirstFragment()
            1 -> fragment = SecondFragment()
        }
        return fragment
    }

}