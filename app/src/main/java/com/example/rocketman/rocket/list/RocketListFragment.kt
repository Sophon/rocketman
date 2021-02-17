package com.example.rocketman.rocket.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rocketman.R
import com.example.rocketman.databinding.FragmentRocketListBinding
import com.example.rocketman.rocket.Repo
import com.example.rocketman.rocket.Rocket
import com.google.android.material.appbar.MaterialToolbar
import timber.log.Timber

class RocketListFragment: Fragment() {

    private lateinit var binding: FragmentRocketListBinding
    private lateinit var toolbar: MaterialToolbar
    private val vm by lazy {
        ViewModelProvider(this).get(RocketListVM::class.java)
    }

    //region lifecycle
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

    override fun onResume() {
        super.onResume()

        setupToolbar()
    }

    override fun onPause() {
        super.onPause()

        Timber.d("toolbar: clearing up")
        toolbar.menu.clear()
    }

    //endregion

    private fun setupToolbar() {
        Timber.d("toolbar: setting up")
        requireActivity().findViewById<MaterialToolbar>(R.id.toolbar_home).apply {
            toolbar = this
            inflateMenu(R.menu.rocket_list)

            vm.activeOnly.observe(viewLifecycleOwner) {
                menu.findItem(R.id.menu_check_active).isChecked = it
            }

            setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.menu_check_active -> {
                        vm.toggleActiveOnly()
                        true
                    }
                    R.id.menu_refresh -> {
                        vm.updateRockets()
                        true
                    }
                    else -> super.onOptionsItemSelected(it)
                }
            }
        }
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