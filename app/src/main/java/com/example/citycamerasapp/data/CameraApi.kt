package com.example.citycamerasapp.data

import retrofit2.Call
import retrofit2.http.GET
interface CameraApi {
    @GET("/api/v2/cameras/public")
    fun getCameras(): Call<List<Camera>>
}