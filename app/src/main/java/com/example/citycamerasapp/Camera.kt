package com.example.citycamerasapp

import com.google.gson.annotations.SerializedName

data class Camera(
    val id: Int,
    @SerializedName("title")
    val StreetName: String,
    val city: String,
)