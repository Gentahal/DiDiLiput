package com.idn.diliput.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.idn.diliput.ui.fragment.*

class ViewPagerAdapter(fragment: Fragment)
    : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 6

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()
        when(position) {
            0 -> fragment = FirstFragment("general")
            1 -> fragment = FirstFragment("sports")
            2 -> fragment = FirstFragment("business")
            3 -> fragment = FirstFragment("health")
            4 -> fragment = FirstFragment("technology")
            5 -> fragment = FirstFragment("science")
        }
        return fragment
    }

}