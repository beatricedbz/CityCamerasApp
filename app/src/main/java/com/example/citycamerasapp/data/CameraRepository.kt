package com.example.citycamerasapp.data

class CameraRepository(private val cameraApi: CameraApi) {
    fun getAllCameras() = cameraApi.getCameras()
}