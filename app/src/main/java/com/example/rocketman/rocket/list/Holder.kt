package com.example.rocketman.rocket.list

import androidx.recyclerview.widget.RecyclerView
import com.example.rocketman.R
import com.example.rocketman.databinding.ItemRocketBinding
import com.example.rocketman.rocket.Rocket
import com.squareup.picasso.Picasso

class Holder(
    private val binding: ItemRocketBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(rocket: Rocket) {
        binding.apply {
            txtName.text = rocket.name
            txtFirstFlight.text = rocket.firstFlight

            if(rocket.flickrImages.isNotEmpty()) {
                Picasso.get()
                    .load(rocket.flickrImages[0])
                    .placeholder(R.drawable.ic_rocket)
                    .fit()
                    .centerCrop()
                    .into(imgRocket)
            }
        }
    }
}