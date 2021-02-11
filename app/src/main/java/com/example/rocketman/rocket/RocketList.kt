package com.example.rocketman.rocket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rocketman.databinding.FragmentRocketsDataBinding
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {
        vm.rockets.observe(viewLifecycleOwner) {
            Timber.d("bullshit: ${it.size} rockets fetched")
        }
    }
}