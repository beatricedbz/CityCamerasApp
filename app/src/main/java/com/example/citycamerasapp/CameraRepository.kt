package com.example.citycamerasapp

class CameraRepository(private val cameraApi: CameraApi) {
    fun getAllCameras() = cameraApi.getCameras()
}