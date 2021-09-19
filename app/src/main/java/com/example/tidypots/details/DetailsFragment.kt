package com.example.tidypots.details

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tidypots.R
import com.example.tidypots.database.PlantDatabase
import com.example.tidypots.databinding.DetailsFragmentBinding


class DetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<DetailsFragmentBinding>(
            inflater, R.layout.details_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = DetailsFragmentArgs.fromBundle(requireArguments())

        val dataSource = PlantDatabase.getInstance(application).plantDatabaseDao
        val viewModelFactory = DetailsViewModelFactory(arguments.plant, dataSource)

        val detailsViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(DetailsViewModel::class.java)

        binding.viewModel = detailsViewModel

        binding.setLifecycleOwner(this)

        detailsViewModel.navigateToNursery.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(
                    DetailsFragmentDirections.actionDetailsFragmentToNurseryFragment())
                detailsViewModel.doneNavigating()
            }
        })

        /*val options = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.plant_placeholder)
            .error(R.drawable.plant_placeholder)

        Glide.with(binding.detailsImage.context).load(Uri.parse(arguments.plant.plantImage)).apply(options).into(binding.detailsImage)
*/
        return binding.root
    }

}