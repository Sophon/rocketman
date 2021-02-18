package com.example.rocketman.company

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rocketman.R
import com.example.rocketman.databinding.FragmentCompanyDataBinding
import com.google.android.material.appbar.MaterialToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class CompanyFragment: Fragment() {

    private lateinit var binding: FragmentCompanyDataBinding
    private lateinit var toolbar: MaterialToolbar
    private val vm by viewModel<CompanyVM>()

    //region Lifecycle
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

    override fun onResume() {
        super.onResume()

        setupToolbar()
    }

    override fun onPause() {
        super.onPause()

        toolbar.menu.clear()
    }
    //endregion

    private fun setupToolbar() {
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar_home).apply {
            toolbar = this
            inflateMenu(R.menu.refresh_only)
            setOnMenuItemClickListener {
                vm.updateCompanyInfo()
                true
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