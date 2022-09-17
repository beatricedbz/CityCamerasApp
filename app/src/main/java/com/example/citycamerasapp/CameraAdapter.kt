package com.example.citycamerasapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CameraAdapter: RecyclerView.Adapter<CameraAdapter.MyViewHolder>() {
    var cameras: List<Camera> = emptyList()

    fun fillCameras(list: List<Camera>){
        this.cameras = list
        notifyDataSetChanged()
    }
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val streetName: TextView = itemView.findViewById(R.id.tvStreetName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_camera_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.streetName.text = cameras[position].StreetName
    }

    override fun getItemCount(): Int {
        return cameras.size
    }
}