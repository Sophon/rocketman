package com.example.rocketman.company

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rocketman.R
import com.example.rocketman.databinding.FragmentCompanyDataBinding

class CompanyFragment: Fragment() {

    private lateinit var binding: FragmentCompanyDataBinding
    private val vm by lazy {
        ViewModelProvider(this).get(CompanyVM::class.java)
    }


    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        Repo.init(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompanyDataBinding.inflate(inflater)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbarCompany)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.company, menu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }
    //endregion

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.update -> {
                vm.updateCompanyInfo()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun setupObservers() {
        vm.companyData.observe(
            viewLifecycleOwner
        ) { company ->
            if(company != null) {
                binding.apply {
                    //TODO: use databinding?
                    txtFounder.text = String.format(
                        getString(R.string.formatting_company_founder),
                        company.founder,
                        company.founded
                    )
                    txtDescription.text = company.summary
                    txtEmployees.text = company.employees.toString()
                    txtVehicles.text = company.vehicles.toString()
                    txtLaunch.text = company.launchSites.toString()
                    txtTest.text = company.testSites.toString()
                    txtValuation.text = String.format(
                        getString(R.string.formatting_valuation_usd),
                        company.valuation / 1000000000.0
                    )
                    txtHq.text = company.headquarters.toString()
                }
            }
        }
    }
}