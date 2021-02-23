package com.example.rocketman.features.launch.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rocketman.R
import com.example.rocketman.databinding.FragmentLaunchListBinding
import com.example.rocketman.features.launch.Launch
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchListFragment: Fragment() {

    private var _binding: FragmentLaunchListBinding? = null
    private val binding get() = _binding!!
    private lateinit var toolbar: MaterialToolbar
    private val vm by viewModel<LaunchListVM>()
    private lateinit var dataStore: DataStore<Preferences>

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataStore = requireContext().createDataStore(name = "launches-settings")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLaunchListBinding.inflate(inflater)

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

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
    //endregion

    //region RecyclerView
    private fun setupRecyclerView() {
        binding.rvLaunches.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = Adapter()
        }
    }

    private fun updateList(launchList: List<Launch>) {
        (binding.rvLaunches.adapter as Adapter).submitList(launchList)
    }
    //endregion

    //region DataStore
    private suspend fun savePreference(key: String, filter: LaunchFilter) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = filter.toString()
        }
    }

    private suspend fun getPreference(key: String): LaunchFilter? {
        val dataStoreKey = stringPreferencesKey(key)
        return dataStore.data.first()[dataStoreKey]?.let {
            LaunchFilter.valueOf(it)
        }
    }
    //endregion

    private fun setupObservers() {
        vm.launches.observe(viewLifecycleOwner) {
            updateList(it)
        }
    }

    private fun setupToolbar() {
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar_home).apply {
            toolbar = this
            inflateMenu(R.menu.launch_list)

            setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.menu_refresh -> {
                        vm.updateLaunches()
                        true
                    }
                    R.id.menu_past -> {
                        it.isChecked = true
                        filterLaunches(LaunchFilter.PAST)
                        true
                    }
                    R.id.menu_upcoming -> {
                        it.isChecked = true
                        filterLaunches(LaunchFilter.UPCOMING)
                        true
                    }
                    R.id.menu_all -> {
                        it.isChecked = true
                        filterLaunches(LaunchFilter.ALL)
                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            }

            getFilterFromDataStore()
        }
    }

    //region Filter
    private fun getFilterFromDataStore() {
        toolbar.apply {
            lifecycleScope.launch {
                getPreference(FILTER_LAUNCH_KEY)?.let { filter ->
                    menu.findItem(
                        when(filter) {
                            LaunchFilter.PAST -> R.id.menu_past
                            LaunchFilter.UPCOMING -> R.id.menu_upcoming
                            else -> R.id.menu_all
                        }
                    ).isChecked = true

                    vm.filterLaunches(filter)
                } ?: vm.filterLaunches()
            }
        }
    }

    private fun filterLaunches(filter: LaunchFilter) {
        lifecycleScope.launch {
            savePreference(FILTER_LAUNCH_KEY, filter)
        }

        vm.filterLaunches(filter)
    }
    //endregion
}

private const val FILTER_LAUNCH_KEY = "com.example.rocketman.launch.filter"