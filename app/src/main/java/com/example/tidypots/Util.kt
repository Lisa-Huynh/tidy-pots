package com.example.tidypots

import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tidypots.database.Plant
import java.security.AccessController.getContext

class PlantItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)

fun formatPlants(plants: List<Plant>, resources: Resources) : Spanned {
    val sb = StringBuilder()
    sb.apply {
        append("<h3>HERE ARE YOUR PLANTS</h3>")
        plants.forEach {
            append("<br>")
            append("<b>Name:</b>")
            append("\t${it.plantName}</br>")
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        return Html.fromHtml(sb.toString())
    }
}

fun plantDetailsName(plant: Plant, res: Resources): String {
    return if (plant.plantName == "") {
        res.getString(R.string.unnamed)
    } else {
        plant.plantName
    }
}

fun plantDetailsImage(imageView: ImageView, plant: Plant) {
    Log.i("t-rex", "plant image is: ${plant.plantImage} " )
    val options = RequestOptions()
        .centerCrop()
        .error(R.drawable.plant_placeholder)
    Glide.with(imageView.context).load(plant.plantImage).apply(options).into(imageView)
}