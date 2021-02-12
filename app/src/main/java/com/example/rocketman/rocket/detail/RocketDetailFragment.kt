package com.example.rocketman.rocket.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rocketman.databinding.FragmentRocketDetailBinding
import com.example.rocketman.rocket.Rocket
import kotlinx.android.synthetic.main.fragment_rocket_detail.*

class RocketDetailFragment: Fragment() {

    private lateinit var rocket: Rocket

    private lateinit var binding: FragmentRocketDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rocket = arguments?.getParcelable(ARG_ROCKET_ID)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRocketDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        binding.apply {
            txtName.text = rocket.name
            checkActive.isChecked = rocket.active
            txtFirstFlight.text = "First flight: ${rocket.firstFlight}"
            txt_stages.text = "Stages: ${rocket.stages}"
            txtBoosters.text = "Boosters: ${rocket.boosters}"
            txtCost.text = "Cost per launch: ${rocket.costPerLaunch}"
            txtSuccess.text = "Success pct: ${rocket.successRatePct}%"
            txtCountry.text = "Country: ${rocket.country}"
            txtHeight.text = rocket.height.toString()
            txtMass.text = rocket.mass.toString()
            txtDescription.text = rocket.description
        }
    }

    companion object {
        const val ARG_ROCKET_ID = "rocketId"
    }
}