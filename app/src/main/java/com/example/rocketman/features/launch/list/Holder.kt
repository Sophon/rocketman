package com.example.rocketman.features.launch.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.rocketman.R
import com.example.rocketman.databinding.ItemLaunchBinding
import com.example.rocketman.features.launch.Launch
import com.example.rocketman.features.launch.detail.LaunchDetailFragment
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
        val args = Bundle().apply {
            putParcelable(LaunchDetailFragment.ARG_LAUNCH_ID, launch)
        }
        v?.findNavController()?.navigate(R.id.action_launch_list_to_launch_detail, args)
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