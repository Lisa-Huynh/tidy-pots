package com.example.tidypots.home

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import android.widget.TextView
import com.example.tidypots.R
import com.example.tidypots.database.Plant
import com.example.tidypots.plantDetailsImage
import com.example.tidypots.plantDetailsName

@BindingAdapter("plantDetailsName")
fun TextView.text(plant: Plant) {
    plant?.let {
        text = plantDetailsName(plant, context.resources)
    }
}

@BindingAdapter("detailsPlant")
fun loadImage(imageView: ImageView, plant: Plant) {
    plantDetailsImage(imageView, plant)
}

