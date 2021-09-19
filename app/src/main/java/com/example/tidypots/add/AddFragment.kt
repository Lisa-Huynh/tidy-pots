package com.example.tidypots.add

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tidypots.R
import com.example.tidypots.database.PlantDatabase
import com.example.tidypots.databinding.AddFragmentBinding

class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = AddFragmentBinding.inflate(inflater)
        val application = requireNotNull(this.activity).application
        val dataSource = PlantDatabase.getInstance(application).plantDatabaseDao
        val viewModelFactory = AddViewModelFactory(dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(AddViewModel::class.java)


        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        var editTextName: EditText? = null
        var plantImage: Uri? = null

        var getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri ->
            binding.imagePreview.setImageURI(uri)
            plantImage = uri
        }

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission is granted. Proceed to open Gallery
                    getContent.launch("image/*")
                }
            }

        var mediaPermissionCheck = ContextCompat.checkSelfPermission(application.applicationContext, READ_EXTERNAL_STORAGE)

        // Handle Upload Image button clicked
        binding.uploadImageButton.setOnClickListener {
            if (mediaPermissionCheck == PERMISSION_GRANTED) {
                // If media permission is already granted, proceed to open Gallery
                getContent.launch("image/*")
            } else {
                // If media permission has not been granted (API 23+)
                // request for runtime permission
                requestPermissionLauncher.launch(READ_EXTERNAL_STORAGE)
            }
        }

        // Handle Save button clicked
        fun onSaveClicked() {
            val newName = editTextName
            val newImage = plantImage

            if (newName != null) {
                viewModel.addPlant(newName, newImage)
            } else {
                Log.i("null!", "name is null for some reason!")
            }
            this.findNavController().navigate(
                AddFragmentDirections.actionAddFragmentToNurseryFragment())
            viewModel.onNavigatedToNursery()
        }

        binding.savePlantButton.setOnClickListener {
            editTextName = binding.editTextPlantName
            if (plantImage == null) {
                plantImage = Uri.Builder().scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                    .authority(resources.getResourcePackageName(R.drawable.plant_placeholder))
                    .appendPath(resources.getResourceTypeName(R.drawable.plant_placeholder))
                    .appendPath(resources.getResourceEntryName(R.drawable.plant_placeholder))
                    .build()
            }
            onSaveClicked()
        }

        return binding.root
    }



}