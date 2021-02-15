package com.example.rocketman.companyEvents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rocketman.databinding.FragmentEventsBinding

class EventsFragment: Fragment() {

    private lateinit var binding: FragmentEventsBinding
    private val vm by lazy {
        ViewModelProvider(this).get(EventsVM::class.java)
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
        binding = FragmentEventsBinding.inflate(inflater)

        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }
    //endregion

    //region RV
    private fun setupRecyclerView() {
        binding.rvEvents.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = Adapter(requireContext())
        }
    }

    private fun updateList(eventList: List<Event>) {
        (binding.rvEvents.adapter as Adapter).submitList(eventList)
    }
    //endregion

    private fun setupObservers() {
        vm.events.observe(viewLifecycleOwner) {
            updateList(it)
        }
    }
}