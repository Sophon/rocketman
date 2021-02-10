package com.example.rocketman.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rocketman.databinding.FragmentCompanyDataBinding

class CompanyFragment: Fragment() {

    private lateinit var binding: FragmentCompanyDataBinding
    private val vm by lazy {
        ViewModelProvider(this).get(CompanyVM::class.java)
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
        binding = FragmentCompanyDataBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {
        vm.companyData.observe(
            viewLifecycleOwner
        ) {
            Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()
        }
    }
}