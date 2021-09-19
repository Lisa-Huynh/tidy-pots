package com.example.tidypots.nursery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tidypots.R
import com.example.tidypots.database.Plant
import com.example.tidypots.database.PlantDatabase
import com.example.tidypots.databinding.NurseryFragmentBinding

class NurseryFragment : Fragment() {

    private lateinit var viewModel: NurseryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<NurseryFragmentBinding>(inflater, R.layout.nursery_fragment,container,false)

        // Reference to the app that this fragment is attached to, to pass into view-model factory provider
        val application = requireNotNull(this.activity).application
        // Create an instance of the ViewModel Factory
        val dataSource = PlantDatabase.getInstance(application).plantDatabaseDao
        val viewModelFactory = NurseryViewModelFactory(dataSource, application)
        // Gets reference to NurseryViewModel from factory ^
        viewModel = ViewModelProvider(this, viewModelFactory).get(NurseryViewModel::class.java)

        val adapter = PlantAdapter(application, PlantListener { plant ->
            viewModel.onPlantClicked(plant)
        })
        binding.plantList.adapter = adapter

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.navigateToAdd.observe(viewLifecycleOwner,
        Observer<Boolean?> { navigate ->
            navigate?.let {
                this.findNavController().navigate(
                    NurseryFragmentDirections.actionNurseryFragmentToAddFragment())
                viewModel.onNavigatedToAdd()
            }
        })

        viewModel.plants.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        viewModel.navigateToPlantDetail.observe(viewLifecycleOwner, Observer { plant: Plant? ->
            plant?.let {
                this.findNavController().navigate(
                    NurseryFragmentDirections
                        .actionNurseryFragmentToDetailsFragment(plant))
                viewModel.onPlantDetailsNavigated()
            }
        })

        return binding.root
    }

}