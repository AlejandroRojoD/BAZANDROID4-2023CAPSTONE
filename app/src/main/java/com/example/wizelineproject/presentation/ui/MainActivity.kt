package com.example.wizelineproject.presentation.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.wizelineproject.R
import com.example.wizelineproject.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var host: NavHost
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNav()
    }

    private fun setUpNav() {
        host =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment? ?: return
        navController = host.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNavigationView?.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loginFragment || destination.id == R.id.registerFragment ||
                destination.id == R.id.detailMovieFragment
            ) {
                bottomNavigationView.visibility = View.GONE
            } else {
                bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.log_out_option -> {
                signOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun signOut() {
        val id = navController.currentDestination?.id
        AuthUI.getInstance().signOut(this).addOnCompleteListener {
            when (id) {
                R.id.moviesListFragment -> {
                    Navigation.findNavController(this, R.id.nav_host)
                        .navigate(R.id.action_moviesListFragment_to_loginFragment)
                }
                R.id.topRatedFragment -> {
                    Navigation.findNavController(this, R.id.nav_host)
                        .navigate(R.id.action_topRatedFragment_to_loginFragment)
                }
                R.id.latestMovieFragment -> {
                    Navigation.findNavController(this, R.id.nav_host)
                        .navigate(R.id.action_latestMovieFragment_to_loginFragment)
                }
                R.id.detailMovieFragment -> {
                    Navigation.findNavController(this, R.id.nav_host)
                        .navigate(R.id.action_detailMovieFragment_to_loginFragment)
                }
            }
        }
    }

}