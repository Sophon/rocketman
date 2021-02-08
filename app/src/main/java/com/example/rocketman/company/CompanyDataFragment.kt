package com.example.rocketman.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rocketman.databinding.FragmentCompanyDataBinding

class CompanyDataFragment: Fragment() {

    private lateinit var binding: FragmentCompanyDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompanyDataBinding.inflate(inflater)

        return binding.root
    }
}