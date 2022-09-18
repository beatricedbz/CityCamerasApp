package com.example.citycamerasapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
interface CameraApi {
    @GET("/api/v2/cameras/public")
    fun getCameras(): Call<List<Camera>>
}