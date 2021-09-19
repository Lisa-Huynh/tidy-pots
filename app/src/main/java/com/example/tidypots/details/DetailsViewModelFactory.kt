package com.example.tidypots.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tidypots.database.Plant
import com.example.tidypots.database.PlantDatabaseDao
import java.lang.IllegalArgumentException

class DetailsViewModelFactory(
    private val plant: Plant,
    private val dataSource: PlantDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(plant, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}