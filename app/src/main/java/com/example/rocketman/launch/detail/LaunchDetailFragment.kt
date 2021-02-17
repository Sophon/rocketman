package com.example.rocketman.launch.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rocketman.databinding.FragmentLaunchDetailBinding

class LaunchDetailFragment: Fragment() {

    private lateinit var binding: FragmentLaunchDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLaunchDetailBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        const val ARG_LAUNCH_ID = "launchId"
    }
}