package com.example.rocketman.features.rocket.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rocketman.R
import com.example.rocketman.databinding.FragmentRocketDetailBinding
import com.google.android.material.appbar.MaterialToolbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_rocket_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RocketDetailFragment: Fragment() {

    private var _binding: FragmentRocketDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var toolbar: MaterialToolbar
    private val vm by viewModel<RocketDetailVM>()

    //region Lifecycle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRocketDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        loadRocket()
    }

    override fun onResume() {
        super.onResume()

        setupToolbar()
    }

    override fun onPause() {
        super.onPause()

        toolbar.menu.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
    //endregion

    private fun setupObservers() {
        vm.rocket.observe(viewLifecycleOwner) { rocket ->
            binding.apply {
                txtName.text = rocket.name
                checkActive.isChecked = rocket.active
                txtFirstFlight.text = String
                    .format(getString(R.string.formatting_rocket_first_flight), rocket.firstFlight)
                txt_stages.text = String
                    .format(getString(R.string.formatting_rocket_stages), rocket.stages)
                txtBoosters.text = String
                    .format(getString(R.string.formatting_rocket_boosters), rocket.boosters)
                txtCost.text = String
                    .format(getString(R.string.formatting_rocket_cost), rocket.costPerLaunch)
                txtSuccess.text = String
                    .format(getString(R.string.formatting_rocket_success), rocket.successRatePct)
                txtCountry.text = String
                    .format(getString(R.string.formatting_rocket_country), rocket.country)
                txtHeight.text = rocket.height.toStringMetric()
                txtMass.text = rocket.mass.toStringMetric()
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

    private fun loadRocket() {
        vm.rocket.postValue(arguments?.getParcelable(ARG_ROCKET_ID))
    }

    private fun setupToolbar() {
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar_home).apply {
            toolbar = this
            inflateMenu(R.menu.refresh_only)
            setOnMenuItemClickListener {
                vm.updateRocket()
                true
            }
        }
    }

    companion object {
        const val ARG_ROCKET_ID = "rocketId"
    }
}