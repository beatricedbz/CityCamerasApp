package com.example.citycamerasapp.di

import com.example.citycamerasapp.data.CameraApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    fun provideCameraApi(retrofitClient: Retrofit): CameraApi {
        return retrofitClient.create(CameraApi::class.java)
    }

    @Provides
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    companion object{
        const val BASE_URL = "https://orionnet.online"
    }
}