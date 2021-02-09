package com.example.rocketman.drawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.rocketman.R
import com.example.rocketman.databinding.FragmentDrawerBinding

private const val KEY_SELECTED_DRAWER_ITEM = "DRAWER_SELECTED_ITEM_ID_KEY"

class DrawerFragment: Fragment() {

    private lateinit var binding: FragmentDrawerBinding
    private var drawerSelectedItemId = R.id.home

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDrawerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.let {
            drawerSelectedItemId = it.getInt(KEY_SELECTED_DRAWER_ITEM, drawerSelectedItemId)
        }

        setupDrawer()
        setBackPressedHandler()
    }

    private fun setBackPressedHandler() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (binding.drawerLayout.isOpen) {
                binding.drawerLayout.close()
            } else {
                findNavController().popBackStack()
            }
        }
    }

    // Needed to maintain correct state over rotations
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(KEY_SELECTED_DRAWER_ITEM, drawerSelectedItemId)
        super.onSaveInstanceState(outState)
    }

    private fun setupDrawer() {
        val controller = binding.drawerNavView.setupWithNavController(
            childFragmentManager,
            findNavController(),
            listOf(
                R.navigation.home,
                R.navigation.rocket,
                R.navigation.company
            ),
            R.id.drawer_container,
            drawerSelectedItemId,
            requireActivity().intent
        )

        controller.observe(
            viewLifecycleOwner,
            { navController ->
                NavigationUI.setupWithNavController(
                    binding.toolbarHome,
                    navController,
                    binding.drawerLayout
                )

                drawerSelectedItemId = navController.graph.id
            }
        )
    }
}