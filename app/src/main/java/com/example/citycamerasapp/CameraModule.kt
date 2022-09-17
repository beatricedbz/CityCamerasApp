package com.example.citycamerasapp

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class CameraModule {

    @Provides
    fun provideCameraRepository(cameraApi: CameraApi): CameraRepository{
        return CameraRepository(cameraApi)
    }
}