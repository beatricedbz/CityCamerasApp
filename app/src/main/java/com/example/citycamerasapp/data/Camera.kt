package com.example.citycamerasapp.data

import com.google.gson.annotations.SerializedName

data class Camera(
    val id: Int,
    @SerializedName("title")
    val streetName: String,
    val city: String,
)