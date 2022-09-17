package com.example.citycamerasapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CameraApi {
    @GET("/api/v2/cameras/public")
    fun getCameras(): Call<List<Camera>>
}