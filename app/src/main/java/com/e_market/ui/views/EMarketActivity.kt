package com.e_market.ui.views

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.e_market.R
import com.e_market.databinding.ActivityEmarketBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EMarketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmarketBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmarketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mobile_navigation) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_cart, R.id.navigation_favorites))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.mobile_navigation)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}