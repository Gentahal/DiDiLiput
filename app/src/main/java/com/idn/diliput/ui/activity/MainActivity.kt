package com.idn.diliput.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.idn.diliput.R
import com.idn.diliput.adapter.ViewPagerAdapter
import com.idn.diliput.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        bottomNavBar()
    }

    private fun bottomNavBar() {
        val nav = binding.navView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        nav.setupWithNavController(navHostFragment.navController)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home,
                R.id.search,
                R.id.bookmark
            )
        )

        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
    }

}