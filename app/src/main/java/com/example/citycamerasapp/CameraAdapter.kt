package com.example.citycamerasapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class CameraAdapter(private val clickListener: (Camera) -> Unit): RecyclerView.Adapter<CameraAdapter.MyViewHolder>() {
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
        val clickOnImage = cameras[position]
        holder.tvStreetName.text = "${cameras[position].city}, ${cameras[position].streetName}"
        Glide.with(holder.itemView.context)
            .load("https://krkvideo14.orionnet.online/cam${cameras[position].id}/preview.jpg")
            .into(holder.ivPreview)
        holder.ivPreview.setOnClickListener{clickListener(clickOnImage)}
    }

    override fun getItemCount(): Int {
        return cameras.size
    }
}