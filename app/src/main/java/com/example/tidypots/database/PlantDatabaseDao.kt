package com.example.tidypots.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PlantDatabaseDao {

    @Insert
    suspend fun insert(plant: Plant)

    @Update
    suspend fun update(plant: Plant)

    @Query("SELECT * from nursery_table WHERE plantId = :key")
    suspend fun get(key: Long): Plant


    @Query("DELETE FROM nursery_table WHERE plant_name = :key")
    suspend fun delete(key: String)

    @Query("DELETE FROM nursery_table")
    suspend fun clear()

    @Query("SELECT * FROM nursery_table ORDER BY plantId DESC")
    fun getAllPlants(): LiveData<List<Plant>>

}