package com.example.rocketman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.rocketman.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.fragment_nav_host)
                as NavHostFragment)
            .navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMain)

        setupNavigationDrawer()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupNavigationDrawer() {
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
            .also { toggle ->
                binding.drawerLayout.addDrawerListener(toggle)
                toggle.syncState() //TODO: find out what it does
            }

        binding.navView.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)

        setupNavigation()
    }

    //region Navigation
    private fun setupNavigation() {
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.company_data -> {
                    displayToast("Company data")
                }

                R.id.rockets_data -> {
                    displayToast("Rocket data")
                }

                R.id.past_launches -> {
                    displayToast("Past launches")
                }

                R.id.upcoming_launches -> {
                    displayToast("Upcoming launches")
                }
            }

            super.onOptionsItemSelected(menuItem)
        }
    }

    private fun displayToast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.drawerLayout)
    }

    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    //endregion
}