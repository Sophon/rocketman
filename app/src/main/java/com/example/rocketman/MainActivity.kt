package com.example.rocketman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.rocketman.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupNavigation()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupNavigation() {
        toggle = ActionBarDrawerToggle(this, binding.drawerMain, R.string.open, R.string.close)
            .also { toggle ->
                binding.drawerMain.addDrawerListener(toggle)
                toggle.syncState() //TODO: find out what it does
            }

        binding.drawerContentMain.setNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.company_data -> {
                    displayToast("Company data")
                }

                R.id.rocket_data -> {
                    displayToast("Rocket data")
                }

                R.id.past_launches -> {
                    displayToast("Past launches")
                }

                R.id.upcoming_launches -> {
                    displayToast("Upcoming launches")
                }
            }

            true
        }
    }

    private fun displayToast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }
}