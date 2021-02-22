package com.example.rocketman.features.launch.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rocketman.R
import com.example.rocketman.databinding.FragmentLaunchDetailBinding
import com.example.rocketman.features.launch.Launch
import com.squareup.picasso.Picasso

class LaunchDetailFragment: Fragment() {

    private lateinit var binding: FragmentLaunchDetailBinding
    private val launch by lazy {
        requireArguments().getParcelable<Launch>(ARG_LAUNCH_ID)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLaunchDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadUIData()
    }

    private fun loadUIData() {
        binding.apply {
            txtName.text = launch.name
            checkSuccess.isChecked = launch.success
            txtDate.text = launch.dateUtc
            txtRocket.text = String
                .format(getString(R.string.formatting_launch_rocket), launch.rocket)
            checkTdb.isChecked = launch.tdb
            checkNet.isChecked = launch.net
            txtWindow.text = String
                .format(getString(R.string.formatting_launch_window), launch.window)
            txtDescription.text = launch.details

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

    companion object {
        const val ARG_LAUNCH_ID = "launchId"
    }
}