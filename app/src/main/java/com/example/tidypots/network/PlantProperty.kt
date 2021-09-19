package com.example.tidypots.network

import android.os.Parcelable
import android.widget.ImageView

data class PlantProperty(
    val id: String,
    val name: String,
    val image: ImageView,
    val parent: String,
    val children: MutableList<String>) {

}