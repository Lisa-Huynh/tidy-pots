package com.example.tidypots.nursery

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.tidypots.database.Plant
import com.example.tidypots.database.PlantDatabaseDao
import com.example.tidypots.formatPlants
import kotlinx.coroutines.launch

class NurseryViewModel(
    val database: PlantDatabaseDao,
    application: Application) : AndroidViewModel(application) {
    init {
        Log.i("NurseryViewModel", "NurseryViewModel created!")
    }

    // Hold a reference to PlantDatabase via PlantDatabaseDao.
    val plants = database.getAllPlants()
    val plantsString = Transformations.map(plants) { plants ->
        formatPlants(plants, application.resources) }



    // Delete a plant from nursery
    fun deletePlant(name: String) {
        viewModelScope.launch {
            delete(name)
        }
    }
    suspend fun delete(name: String) {
        database.delete(name)
    }

    // Navigate to Add Fragment
    private val _navigateToAdd = MutableLiveData<Boolean?>()

    val navigateToAdd: LiveData<Boolean?>
        get() = _navigateToAdd
    fun onFabClicked() {
        _navigateToAdd.value = true
    }
    fun onNavigatedToAdd() {
        _navigateToAdd.value = null
    }

    // On plant clicked
    private val _navigateToPlantDetail = MutableLiveData<Plant?>()
    val navigateToPlantDetail : LiveData<Plant?>
        get() = _navigateToPlantDetail
    fun onPlantClicked(plant: Plant) {
        _navigateToPlantDetail.value = plant
    }
    fun onPlantDetailsNavigated() {
        _navigateToPlantDetail.value = null
    }

}