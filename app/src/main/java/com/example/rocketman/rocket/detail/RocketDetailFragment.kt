package com.example.rocketman.rocket.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rocketman.databinding.FragmentRocketDetailBinding

class RocketDetailFragment: Fragment() {

    private lateinit var binding: FragmentRocketDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRocketDetailBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        const val ARG_ROCKET_ID = "rocketId"
    }
}