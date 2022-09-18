package com.example.citycamerasapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CameraAdapter: RecyclerView.Adapter<CameraAdapter.MyViewHolder>() {
    var cameras: List<Camera> = emptyList()

    fun fillCameras(list: List<Camera>){
        this.cameras = list
        notifyDataSetChanged()
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
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
        holder.tvStreetName.text = "${cameras[position].city}, ${cameras[position].StreetName}"
        Glide.with(holder.itemView.context)
            .load("https://krkvideo14.orionnet.online/cam${cameras[position].id}/preview.jpg")
            .into(holder.ivPreview)
    }

    override fun getItemCount(): Int {
        return cameras.size
    }
}