package com.example.rocketman.launch.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rocketman.R
import com.example.rocketman.databinding.FragmentLaunchListBinding
import com.example.rocketman.launch.Launch
import com.example.rocketman.launch.Repo
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.fragment_drawer.view.*

class LaunchListFragment: Fragment() {

    private lateinit var binding: FragmentLaunchListBinding
    private lateinit var toolbar: MaterialToolbar
    private val vm by lazy {
        ViewModelProvider(this).get(LaunchListVM::class.java)
    }

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Repo.init(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLaunchListBinding.inflate(inflater)

        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
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
            inflateMenu(R.menu.launch_list)

            vm.launchStatus.observe(viewLifecycleOwner) {
                when(it) {
                    LaunchFilter.UPCOMING -> {
                        menu.findItem(R.id.menu_upcoming).isChecked = true
                    }
                    LaunchFilter.PAST -> {
                        menu.findItem(R.id.menu_past).isChecked = true
                    }
                    LaunchFilter.ALL -> {
                        menu.findItem(R.id.menu_all).isChecked = true
                    }
                }
            }

            setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.menu_refresh -> {
                        vm.updateLaunches()
                        true
                    }
                    R.id.menu_past -> {
                        vm.filterLaunches(LaunchFilter.PAST)
                        true
                    }
                    R.id.menu_upcoming -> {
                        vm.filterLaunches(LaunchFilter.UPCOMING)
                        true
                    }
                    R.id.menu_all -> {
                        vm.filterLaunches(LaunchFilter.ALL)
                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            }
        }
    }

    //region RecyclerView
    private fun setupRecyclerView() {
        binding.rvLaunches.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = Adapter(requireContext())
        }
    }

    private fun updateList(launchList: List<Launch>) {
        (binding.rvLaunches.adapter as Adapter).submitList(launchList)
    }
    //endregion

    private fun setupObservers() {
        vm.launches.observe(viewLifecycleOwner) {
            updateList(it)
        }
    }
}