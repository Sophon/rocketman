package com.example.rocketman.rocket.list

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rocketman.R
import com.example.rocketman.databinding.FragmentRocketListBinding
import com.example.rocketman.rocket.Repo
import com.example.rocketman.rocket.Rocket

class RocketListFragment: Fragment() {

    private lateinit var binding: FragmentRocketListBinding
    private val vm by lazy {
        ViewModelProvider(this).get(RocketListVM::class.java)
    }

    //region lifecycle
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
        binding = FragmentRocketListBinding.inflate(inflater)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbarRocket)

        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }
    //endregion

    //region menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.rocket_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_check_active -> {
                vm.toggleActiveOnly()
                item.isChecked = !item.isChecked
                true
            } else -> {
                super.onOptionsItemSelected(item)
            }
        }
    //        return super.onOptionsItemSelected(item)
    }

    //endregion

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