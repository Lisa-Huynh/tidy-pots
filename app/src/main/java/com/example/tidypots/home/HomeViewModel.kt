package com.example.tidypots.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.example.tidypots.database.PlantDatabaseDao
import kotlinx.coroutines.launch

class HomeViewModel(val database: PlantDatabaseDao, application: Application) : ViewModel() {

    // Navigate to Nursery Fragment
    private val _navigateToNursery = MutableLiveData<Boolean>()
    val navigateToNursery
        get() = _navigateToNursery
    fun onNurseryClicked() {
        _navigateToNursery.value = true
    }
    fun onNurseryNavigated() {
        _navigateToNursery.value = null
    }


    // To clear all nursery data
    fun onClear() {
        viewModelScope.launch {
            clear()
        }
    }
    suspend fun clear() {
        database.clear()
    }


}