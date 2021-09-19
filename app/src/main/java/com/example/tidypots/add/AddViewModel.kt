package com.example.tidypots.add

import android.app.Application
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.*
import com.example.tidypots.R
import com.example.tidypots.database.Plant
import com.example.tidypots.database.PlantDatabaseDao
import kotlinx.coroutines.launch

class AddViewModel(val database: PlantDatabaseDao,
application: Application): AndroidViewModel(application) {

    init {
        Log.i("AddViewModel", "AddViewModel created!")
    }

    fun addPlant(editText: EditText, imageUri: Uri?) {
        viewModelScope.launch {
            val newPlant = Plant()
            newPlant.plantName = editText.getText().toString()
            newPlant.plantImage = imageUri.toString()
            insert(newPlant)
        }
    }

    private suspend fun insert(plant: Plant) {
        database.insert(plant)
    }

    // Navigate back to Nursery after adding plant
    private val _navigateToNursery = MutableLiveData<Boolean?>()
    val navigateToNursery : LiveData<Boolean?>
        get() = _navigateToNursery
    fun onPlantAdded() {
        _navigateToNursery.value = true
    }
    fun onNavigatedToNursery() {
        _navigateToNursery.value = null
    }
}