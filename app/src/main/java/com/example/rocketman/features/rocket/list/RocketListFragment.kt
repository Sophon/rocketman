package com.example.rocketman.features.rocket.list

import android.os.Bundle
import android.view.*
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rocketman.R
import com.example.rocketman.databinding.FragmentRocketListBinding
import com.example.rocketman.features.rocket.Rocket
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class RocketListFragment: Fragment() {

    private lateinit var binding: FragmentRocketListBinding
    private lateinit var toolbar: MaterialToolbar
    private val vm by viewModel<RocketListVM>()
    private lateinit var dataStore: DataStore<Preferences>

    //region lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataStore = requireContext().createDataStore(name = "rocket-list-settings")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRocketListBinding.inflate(inflater)

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

    //region RecyclerView
    private fun setupRecyclerView() {
        binding.rvRockets.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = Adapter(requireContext())
        }
    }

    private fun updateList(rocketList: List<Rocket>) {
        (binding.rvRockets.adapter as Adapter).submitList(rocketList)
    }
    //endregion

    //region DataStore
    private suspend fun savePreference(key: String, activeOnly: Boolean) {
        val dataStoreKey = booleanPreferencesKey(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = activeOnly
        }
    }

    private suspend fun getPreference(key: String): Boolean? {
        val dataStoreKey = booleanPreferencesKey(key)
        return dataStore.data.first()[dataStoreKey]
    }
    //endregion

    private fun setupObservers() {
        vm.rockets.observe(viewLifecycleOwner) {
            updateList(it)
        }
    }

    private fun setupToolbar() {
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar_home).apply {
            toolbar = this
            inflateMenu(R.menu.rocket_list)

            setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.menu_check_active_rockets_only -> {
                        vm.activeOnly.apply {
                            it.isChecked = !this
                            toggleActiveOnly(!this)
                        }

                        true
                    }
                    R.id.menu_refresh -> {
                        vm.updateRockets()
                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            }

            getActiveOnlyFromDataStore()
        }
    }

    //region Active only
    private fun getActiveOnlyFromDataStore() {
        toolbar.apply {
            lifecycleScope.launch {
                getPreference(ROCKETS_ACTIVE_KEY)?.let { activeOnly ->
                    menu.findItem(R.id.menu_check_active_rockets_only).isChecked = activeOnly
                    vm.toggleActiveOnly(activeOnly)
                }
            }
        }
    }

    private fun toggleActiveOnly(activeOnly: Boolean) {
        lifecycleScope.launch {
            savePreference(ROCKETS_ACTIVE_KEY, activeOnly)
        }

        vm.toggleActiveOnly(activeOnly)
    }
    //endregion
}

private const val ROCKETS_ACTIVE_KEY = "com.example.rockeman.rocket.activeOnlyKey"