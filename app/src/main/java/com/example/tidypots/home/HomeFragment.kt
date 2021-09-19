package com.example.tidypots.home

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.tidypots.R
import com.example.tidypots.database.PlantDatabase
import com.example.tidypots.databinding.HomeFragmentBinding
import com.example.tidypots.nursery.NurseryViewModel
import com.example.tidypots.nursery.NurseryViewModelFactory

/**
 * Home Fragment where everything is
 */

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<HomeFragmentBinding>(inflater, R.layout.home_fragment,container,false)

        val application = requireNotNull(this.activity).application
        // Create an instance of the ViewModel Factory
        val dataSource = PlantDatabase.getInstance(application).plantDatabaseDao
        val viewModelFactory = HomeViewModelFactory(dataSource, application)
        // Gets reference to NurseryViewModel from factory ^
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.navigateToNursery.observe(viewLifecycleOwner, Observer { flag ->
            flag?.let {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToNurseryFragment())
                viewModel.onNurseryNavigated()
            }
        })



        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


}