package com.example.tidypots.nursery

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tidypots.database.PlantDatabaseDao
import java.lang.IllegalArgumentException

class NurseryViewModelFactory(
    private val dataSource: PlantDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NurseryViewModel::class.java)) {
            return NurseryViewModel(dataSource, application) as T
            //return NurseryViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}