package com.example.citycamerasapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.citycamerasapp.R
import com.example.citycamerasapp.data.Camera
import java.util.*

class CameraAdapter(private val clickListener: (Camera) -> Unit) :
    ListAdapter<Camera, CameraAdapter.MyViewHolder>(
        CameraDiffUtil) {

    private var unfilteredList = listOf<Camera>()

    fun modifyList(list: List<Camera>) {
        unfilteredList = list
        submitList(list)
    }

    fun filter(query: CharSequence?) {
        val list = mutableListOf<Camera>()

        if (!query.isNullOrEmpty()) {
            list.addAll(unfilteredList.filter {
                it.streetName.lowercase(Locale.getDefault()).contains(query.toString()
                    .lowercase(Locale.getDefault())) ||
                        it.city.lowercase(Locale.getDefault()).contains(query.toString()
                            .lowercase(Locale.getDefault()))
            })
        } else {
            list.addAll(unfilteredList)
        }
        submitList(list)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvStreetName: TextView = itemView.findViewById(R.id.tvStreetName)
        val ivPreview: ImageView = itemView.findViewById(R.id.ivCamera)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_camera_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val camera = getItem(position)
        holder.tvStreetName.text = "${camera.city}, ${camera.streetName}"
        Glide
            .with(holder.itemView.context)
            .load("https://krkvideo14.orionnet.online/cam${camera.id}/preview.jpg")
            .into(holder.ivPreview)
        holder.ivPreview.setOnClickListener { clickListener(camera) }

    }

    object CameraDiffUtil : DiffUtil.ItemCallback<Camera>() {
        override fun areItemsTheSame(oldItem: Camera, newItem: Camera): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Camera, newItem: Camera): Boolean {
            return oldItem.city == newItem.city &&
                    oldItem.streetName == newItem.streetName
        }

    }
}