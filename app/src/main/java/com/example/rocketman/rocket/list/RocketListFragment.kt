package com.example.rocketman.rocket.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rocketman.databinding.FragmentRocketListBinding
import com.example.rocketman.rocket.Repo
import com.example.rocketman.rocket.Rocket

class RocketListFragment: Fragment() {

    private lateinit var binding: FragmentRocketListBinding
    private val vm by lazy {
        ViewModelProvider(this).get(RocketListVM::class.java)
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
        binding = FragmentRocketListBinding.inflate(inflater)

        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

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

    private fun setupObservers() {
        vm.rockets.observe(viewLifecycleOwner) {
            updateList(it)
        }
    }
}