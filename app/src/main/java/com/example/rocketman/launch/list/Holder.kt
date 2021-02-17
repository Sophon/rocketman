package com.example.rocketman.launch.list

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rocketman.R
import com.example.rocketman.databinding.ItemLaunchBinding
import com.example.rocketman.launch.Launch
import com.squareup.picasso.Picasso

class Holder(
    private val context: Context,
    private val binding: ItemLaunchBinding
): RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var launch: Launch

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    fun bind(launch: Launch) {
        this.launch = launch

        binding.apply {
            txtName.text = launch.name
            txtDate.text = launch.dateUtc
            txtDetails.text = launch.details

            if(launch.links.flickr.original.isNotEmpty()) {
                Picasso.get()
                    .load(launch.links.flickr.original[0])
                    .placeholder(R.drawable.ic_rocket_launch)
                    .fit()
                    .centerCrop()
                    .into(imgLaunch)
            }
        }

    }
}