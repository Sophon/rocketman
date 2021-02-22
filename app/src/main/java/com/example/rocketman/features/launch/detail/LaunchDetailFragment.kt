package com.example.rocketman.features.launch.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rocketman.R
import com.example.rocketman.databinding.FragmentLaunchDetailBinding
import com.google.android.material.appbar.MaterialToolbar
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchDetailFragment: Fragment() {

    private lateinit var binding: FragmentLaunchDetailBinding
    private lateinit var toolbar: MaterialToolbar
    private val vm by viewModel<LaunchDetailVM>()

    //region Lifecycle
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
    //endregion

    private fun setupToolbar() {
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar_home).apply {
            toolbar = this
            inflateMenu(R.menu.refresh_only)
            setOnMenuItemClickListener {
                vm.updateLaunch()
                true
            }
        }
    }

    private fun setupObservers() {
        vm.launch.observe(viewLifecycleOwner) { launch ->
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
    }

    private fun loadRocket() {
        vm.launch.postValue(arguments?.getParcelable(ARG_LAUNCH_ID))
    }

    companion object {
        const val ARG_LAUNCH_ID = "launchId"
    }
}