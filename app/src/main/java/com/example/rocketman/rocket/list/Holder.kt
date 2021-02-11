package com.example.rocketman.rocket.list

import androidx.recyclerview.widget.RecyclerView
import com.example.rocketman.databinding.ItemRocketBinding
import com.example.rocketman.rocket.Rocket

class Holder(
    private val binding: ItemRocketBinding
): RecyclerView.ViewHolder(binding.root) {

    private lateinit var rocket: Rocket

    fun bind(rocket: Rocket) {
        this.rocket = rocket

        binding.apply {
            txtName.text = rocket.name
            txtFirstFlight.text = rocket.firstFlight
        }
    }
}