package com.example.rocketman.features.rocket.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rocketman.R
import com.example.rocketman.databinding.ItemRocketBinding
import com.example.rocketman.features.rocket.Rocket
import com.example.rocketman.features.rocket.detail.RocketDetailFragment
import com.squareup.picasso.Picasso

class Holder(
    private val context: Context,
    private val binding: ItemRocketBinding
): RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var rocket: Rocket

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val args = Bundle().apply {
            putParcelable(RocketDetailFragment.ARG_ROCKET_ID, rocket)
        }
        v?.findNavController()?.navigate(R.id.action_rocket_list_to_rocket_detail, args)
    }

    fun bind(rocket: Rocket) {
        this.rocket = rocket

        binding.apply {
            txtName.text = rocket.name
            txtFirstFlight.text = String.format(
                context.getString(R.string.formatting_rocket_first_flight),
                rocket.firstFlight
            )
            txtDescription.text = rocket.description

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