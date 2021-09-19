package com.example.tidypots.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tidypots.database.Plant
import com.example.tidypots.database.PlantDatabaseDao

class DetailsViewModel(
    val plant: Plant,
dataSource: PlantDatabaseDao) : ViewModel() {

    val database = dataSource

    // Navigate back to nursery
    private val _navigateToNursery = MutableLiveData<Boolean?>()
    val navigateToNursery: LiveData<Boolean?>
        get() = _navigateToNursery
    fun doneNavigating() {
        _navigateToNursery.value = null
    }
    fun onClose() {
        _navigateToNursery.value = true
    }


}