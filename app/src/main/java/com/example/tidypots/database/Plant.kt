package com.example.tidypots.database

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tidypots.R
import kotlinx.parcelize.Parcelize

// Represents one plant with all its info

@Entity(tableName = "nursery_table")
@Parcelize
data class Plant (
    @PrimaryKey(autoGenerate = true)
    var plantId: Long = 0L,

    @ColumnInfo(name = "plant_name")
    var plantName: String = "",

    @ColumnInfo(name = "plant_image")
    var plantImage: String = "",

    //@ColumnInfo(name = "plant_mortality")
    //var plantMortality: LiveData<Boolean>

    //@ColumnInfo(name = "plant_parent")
    //var plantParent: String = "",

    //@ColumnInfo(name = "plant_children")
    //var plantChildren: MutableList<String>

) : Parcelable