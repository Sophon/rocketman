package com.example.rocketman.drawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.rocketman.R
import com.example.rocketman.databinding.FragmentDrawerBinding
import kotlinx.android.synthetic.main.fragment_drawer.*

class DrawerFragment: Fragment() {

    private var _binding: FragmentDrawerBinding? = null
    private val binding get() = _binding!!
    private var drawerSelectedItemId = R.id.nav_events

    //region Lifecycle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDrawerBinding.inflate(inflater)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar_home)
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

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    //endregion

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
                R.navigation.nav_event,
                R.navigation.nav_rocket,
                R.navigation.nav_launch,
                R.navigation.nav_company
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

private const val KEY_SELECTED_DRAWER_ITEM = "DRAWER_SELECTED_ITEM_ID_KEY"
