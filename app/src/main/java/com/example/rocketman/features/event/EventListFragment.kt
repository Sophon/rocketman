package com.example.rocketman.features.event

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
import com.example.rocketman.databinding.FragmentEventsBinding
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventListFragment: Fragment() {

    private lateinit var binding: FragmentEventsBinding
    private lateinit var toolbar: MaterialToolbar
    private val vm by viewModel<EventListVM>()
    private lateinit var dataStore: DataStore<Preferences>

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataStore = requireContext().createDataStore(name = "events-settings")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventsBinding.inflate(inflater)

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

    //region RV
    private fun setupRecyclerView() {
        binding.rvEvents.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = Adapter()
        }
    }

    private fun updateList(eventList: List<Event>) {
        (binding.rvEvents.adapter as Adapter).submitList(eventList)
    }
    //endregion

    //region Datastore
    private suspend fun savePreference(key: String, sorting: Sorting) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = sorting.toString()
        }
    }

    private suspend fun getPreference(key: String): Sorting? {
        val dataStoreKey = stringPreferencesKey(key)
        return dataStore.data.first()[dataStoreKey]?.let {
            Sorting.valueOf(it)
        }
    }
    //endregion

    private fun setupObservers() {
        vm.events.observe(viewLifecycleOwner) {
            updateList(it)
        }
    }

    private fun setupToolbar() {
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar_home).apply {
            toolbar = this
            inflateMenu(R.menu.events)
            setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.menu_refresh -> {
                        vm.updateEvents()
                        true
                    }
                    R.id.ascending -> {
                        it.isChecked = true

                        sortEvents(Sorting.ASCENDING)

                        true
                    }
                    R.id.descending -> {
                        it.isChecked = true

                        sortEvents(Sorting.DESCENDING)

                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            }

            updateMenuSorting()
        }
    }

    //region Sorting
    private fun updateMenuSorting() {
        toolbar.apply {
            lifecycleScope.launch {
                getPreference(SORT_PREF_KEY)?.let { sorting ->
                    menu.findItem(
                        if(sorting == Sorting.ASCENDING) R.id.ascending
                        else R.id.descending
                    ).isChecked = true

                    vm.updateSorting(sorting)
                }
            }
        }
    }

    private fun sortEvents(sorting: Sorting) {
        lifecycleScope.launch {
            savePreference(SORT_PREF_KEY, sorting)
        }

        vm.updateSorting(sorting)
    }
    //endregion
}

private const val SORT_PREF_KEY = "com.example.rocketman.event.sortPrefKey"