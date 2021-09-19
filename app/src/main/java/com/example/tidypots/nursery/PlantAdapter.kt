package com.example.tidypots.nursery

import android.app.Application
import android.content.ContentResolver
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tidypots.R
import com.example.tidypots.database.Plant
import com.example.tidypots.databinding.ListItemPlantBinding


class PlantAdapter(val application: Application, val clickListener: PlantListener) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {
    var data = listOf<Plant>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, application)
    }

    class ViewHolder private constructor(val binding : ListItemPlantBinding, val application: Application) : RecyclerView.ViewHolder(binding.root) {
        val plantName: TextView = itemView.findViewById(R.id.plant_string)
        val plantImage: ImageView = itemView.findViewById(R.id.plant_image)

        fun bind(item: Plant, clickListener: PlantListener) {
            val res = itemView.context.resources
            binding.plant = item
            binding.clickListener = clickListener
            if (item.plantImage != "") {
                val options = RequestOptions()
                    .centerCrop()
                    .placeholder(R.drawable.plant_placeholder)
                    .error(R.drawable.plant_placeholder)

                Glide.with(application.applicationContext).load(Uri.parse(item.plantImage)).apply(options).into(plantImage)


            } else {
                plantImage.setImageURI(
                    Uri.Builder().scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                    .authority(res.getResourcePackageName(R.drawable.plant_placeholder))
                    .appendPath(res.getResourceTypeName(R.drawable.plant_placeholder))
                    .appendPath(res.getResourceEntryName(R.drawable.plant_placeholder))
                    .build())
            }
            plantName.text = item.plantName
        }

        companion object {
            fun from(parent: ViewGroup, application: Application): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                //val view = layoutInflater.inflate(R.layout.list_item_plant, parent, false)
                val binding = ListItemPlantBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, application)
            }
        }
    }

}

class PlantListener(val clickListener: (plant: Plant) -> Unit) {
    fun onClick(plant: Plant) = clickListener(plant)
}