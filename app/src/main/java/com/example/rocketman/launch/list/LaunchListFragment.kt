package com.example.rocketman.launch.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rocketman.databinding.FragmentLaunchListBinding
import com.example.rocketman.launch.Launch
import com.example.rocketman.launch.Repo

class LaunchListFragment: Fragment() {

    private lateinit var binding: FragmentLaunchListBinding
    private val vm by lazy {
        ViewModelProvider(this).get(LaunchListVM::class.java)
    }

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