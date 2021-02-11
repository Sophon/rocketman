package com.example.rocketman.rocket.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.rocketman.databinding.FragmentRocketsDataBinding
import com.example.rocketman.rocket.Repo
import com.example.rocketman.rocket.Rocket
import timber.log.Timber

class RocketList: Fragment() {

    private lateinit var binding: FragmentRocketsDataBinding
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
        binding = FragmentRocketsDataBinding.inflate(inflater)

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
            adapter = Adapter()
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